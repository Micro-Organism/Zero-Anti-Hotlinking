package com.zero.anti.hotlinking.common.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TokenValidator {

    public static boolean validateToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");
        //set your predefined token here
        String validToken = "your-predefined-token";

        if (token == null || !token.equals(validToken)) {
            response.getWriter().write("403 Forbidden: Invalid Token");
            return false;
        }

        return true;
    }
}