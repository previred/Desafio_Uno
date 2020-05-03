package com.previred.lost.periods.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Set of operations to manipulate and format Strings.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
public final class StringFormatUtils {

    private StringFormatUtils() {

    }

    /**
     * Apply a String format mask to a {@link Date}, using the ISO Date Format.
     * 
     * @return String that represents a date into ISO format (i.e. 2020-05-02
     *         14:03:47.896Z).
     */
    public static String getTodayISODate() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Build and return String REST endpoint.
     * 
     * @param protocol Service protocol ("http://" or "://").
     * @param host     Host name or IP address of service (i.e. "127.0.0.1",
     *                 "localhost").
     * @param port     Port number of service, recommended value between [1100 -
     *                 65535].
     * @param path     API operation context path started with "/" character (i.e.
     *                 "/resource/operation").
     * @return String URL with the following structure:
     *         <b>"&#60;protocol://&#62;&#60;host&#62;:&#60;port&#62;&#60;/path&#62;"</b>
     *         (i.e. http://127.0.0.1:8080/tickets-api/list).
     */
    public static String getUrlService(String protocol, String host, String port, String path) {
        return String.format("%s%s%s%s", protocol, host, ":" + port, path);
    }

}
