package com.izzaz.wcc.javachallenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izzaz.wcc.javachallenge.model.request.RequestHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderUtil {
    public static RequestHeaders getHeaders(HttpServletRequest request){
        final ObjectMapper mapper = new ObjectMapper();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headersMap = new HashMap<>();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        return mapper.convertValue(headersMap,RequestHeaders.class);
    }
}
