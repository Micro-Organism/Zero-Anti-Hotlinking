package com.zero.anti.hotlinking.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StaticResourceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // validate Referer
       String referer = httpRequest.getHeader("Referer");
        String allowedDomain = "http://localhost:8088";
        if (referer == null || !referer.startsWith(allowedDomain)) {
            httpResponse.getWriter().write("403 Forbidden: Hotlinking not allowed");
            return;
        }

        // validate Token
        if (!TokenValidator.validateToken(httpRequest, httpResponse)) {
            return;
        }

        // validate Timestamp
        if (!TimeValidator.validateTimestamp(httpRequest, httpResponse)) {
            return;
        }

        chain.doFilter(request, response);
    }
}