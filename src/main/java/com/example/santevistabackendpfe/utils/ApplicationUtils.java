package com.example.santevistabackendpfe.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ApplicationUtils {
    private ApplicationUtils() {
    }
    public static void setHeaderValue(String header, String value) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            if (response != null) {
                response.setHeader(header, value);
            }
        }
    }
}
