package com.previred.desafiouno.util;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageSourceUtil {

    public static final String DESCRIPCION_DEL_ERROR_NO_ENCONTRADA = "Descripción de mensaje no encontrada";

    private MessageSourceUtil() {
    }

    private static final Locale defaultLocale = new Locale("es", "cl");
    private static ResourceBundle messagesResourceBundle = ResourceBundle.getBundle("message", defaultLocale);


    public static String getValue(String key){
        return getValue(key, null, null);
    }

    private static String getValue(String key, Object[] params, Locale locale){
        try {
            if (locale != null) {
                messagesResourceBundle = ResourceBundle.getBundle("messages", locale);
            } else {
                locale = defaultLocale;
            }

            String pattern = messagesResourceBundle.getString(key);
            pattern = new String(pattern.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            if (params != null && params.length > 0) {
                MessageFormat formatter = new MessageFormat(pattern, locale);
                return formatter.format(params);
            }
            return pattern;
        }catch (MissingResourceException e){
            return DESCRIPCION_DEL_ERROR_NO_ENCONTRADA;
        }
    }

}
