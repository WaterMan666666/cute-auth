package com.waterman.auth.web.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class WebUtils {

    public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";


    public static String getPathWithinApplication(HttpServletRequest request) {
        String contextPath = getContextPath(request);
        String requestUri = getRequestUri(request);
        if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
            String path = requestUri.substring(contextPath.length());
            return (StringUtils.hasText(path) ? path : "/");
        } else {
            return requestUri;
        }
    }

    public static String getContextPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        contextPath = normalize(decodeRequestString(request, contextPath));
        if ("/".equals(contextPath)) {
            // the normalize method will return a "/" and includes on Jetty, will also be a "/".
            contextPath = "";
        }
        return contextPath;
    }

    public static String getRequestUri(HttpServletRequest request) {
       String uri = valueOrEmpty(request.getContextPath()) + "/" +
                    valueOrEmpty(request.getServletPath()) +
                    valueOrEmpty(request.getPathInfo());
       return normalize(decodeAndCleanUriString(request, uri));
    }

    public static String normalize(String path) {
        return normalize(path, true);
    }

    private static String normalize(String path, boolean replaceBackSlash) {

        if (path == null) {
            return null;
        }

        // Create a place for the normalized path
        String normalized = path;

        if (replaceBackSlash && normalized.indexOf('\\') >= 0) {
            normalized = normalized.replace('\\', '/');
        }
        if (normalized.equals("/.")) {
            return "/";
        }

        // Add a leading "/" if necessary
        if (!normalized.startsWith("/")) {
            normalized = "/" + normalized;
        }
        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0) {
                break;
            }
            if (index == 0) {
                return (null);
            }
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Return the normalized path that we have completed
        return (normalized);

    }

    private static String valueOrEmpty(String input) {
        if (input == null) {
            return "";
        }
        return input;
    }

    private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
        uri = decodeRequestString(request, uri);
        int semicolonIndex = uri.indexOf(';');
        return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
    }

    public static String decodeRequestString(HttpServletRequest request, String source) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = DEFAULT_CHARACTER_ENCODING;
        }
        try {
            return URLDecoder.decode(source, enc);
        } catch (UnsupportedEncodingException ex) {
            return source;
        }
    }
}
