package com.edenred.exercise.one.exceptions;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

public class ApiApplicationException extends RuntimeException {

    private final String DEFAULT_ERROR_CODE = "unexpected_error";
    private static final int SC_INTERNAL_SERVER_ERROR = 500;
    private static final ObjectMapper objectMapper = initObjectMapper();
    protected String errorCode;
    protected Integer status;
    protected String message;
    protected String command;
    protected String commandFallback;
    protected String task;
    protected String taskFallback;
    protected String controller;
    protected String displayMessage;
    protected Map<String, String> params;

    public ApiApplicationException(Throwable cause) {
        super(cause);
        this.errorCode = "unexpected_error";
        this.status = 500;
    }

    public ApiApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "unexpected_error";
        this.status = 500;
    }

    public ApiApplicationException(String errorCode, Integer status, String message) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }

    public ApiApplicationException(String errorCode, Integer status, String message, String displayMessage) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
        this.displayMessage = displayMessage;
    }

    public ApiApplicationException(String errorCode, Integer status, String message, String displayMessage, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
        this.displayMessage = displayMessage;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return this.displayMessage;
    }

    public static Throwable setWithDisplayMessage(Throwable throwable, String displayMessage) {
        if (throwable instanceof ApiApplicationException) {
            ((ApiApplicationException)throwable).setDisplayMessage(displayMessage);
        } else {
            throwable = new ApiApplicationException("unexpected_error", 500, ((Throwable)throwable).getMessage(), displayMessage, (Throwable)throwable);
        }

        return (Throwable)throwable;
    }

    public static ApiApplicationException wrapIfNotApiApplicationException(Throwable throwable) {
        return throwable instanceof ApiApplicationException ? (ApiApplicationException)throwable : new ApiApplicationException(throwable);
    }

    public static ApiApplicationException wrapIfNotApiApplicationException(String message, Throwable throwable) {
        return throwable instanceof ApiApplicationException ? (ApiApplicationException)throwable : new ApiApplicationException(message, throwable);
    }


    public void setCommand(String commandKey) {
        this.command = commandKey;
    }

    public void setCommandFallback(String commandKey) {
        this.commandFallback = commandKey;
    }

    public void setTask(String taskName) {
        this.task = taskName;
    }

    public void setTaskFallback(String taskName) {
        this.taskFallback = taskName;
    }

    public void setController(String controllerName) {
        this.controller = controllerName;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static Throwable getNotRxJavaExceptionCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return cause != null && !isRxJavaException(cause) ? getNotRxJavaExceptionCause(cause) : throwable;
    }

    public static Throwable getCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return cause != null ? cause : throwable;
    }

    private static ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter();
        defaultPrettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(defaultPrettyPrinter);
        return objectMapper;
    }

    private String buildPath() {
        StringBuilder sb = new StringBuilder();
        if (this.controller != null) {
            sb.append(" -> controller.action: ").append(this.controller);
        }

        if (this.taskFallback != null) {
            sb.append(" -> task: ").append(this.taskFallback).append(" (fallback)");
        }

        if (this.task != null) {
            sb.append(" -> task: ").append(this.task);
        }

        if (this.commandFallback != null) {
            sb.append(" -> command: ").append(this.commandFallback).append(" (fallback)");
        }

        if (this.command != null) {
            sb.append(" -> command: ").append(this.command);
        }

        return sb.toString();
    }

    private static boolean isRxJavaException(Throwable t) {
        if (t == null) {
            return false;
        } else {
            StackTraceElement[] stackTrace = t.getStackTrace();
            return stackTrace.length > 0 && stackTrace[0].getClassName().startsWith("rx.exceptions");
        }
    }
}
