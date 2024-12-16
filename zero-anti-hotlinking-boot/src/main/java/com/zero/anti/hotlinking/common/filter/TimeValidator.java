package com.zero.anti.hotlinking.common.filter;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

public class TimeValidator {

    // offset in seconds( 300 seconds)
    private static final long ALLOWED_TIME_DIFF = 300;

    public static boolean validateTimestamp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String timestampStr = request.getParameter("timestamp");

        if (timestampStr == null) {
            response.getWriter().write("403 Forbidden: Missing Timestamp");
            return false;
        }

        try {
            long timestamp = Long.parseLong(timestampStr);
            long currentTimestamp = Instant.now().getEpochSecond();

            if (Math.abs(currentTimestamp - timestamp) > ALLOWED_TIME_DIFF) {
                response.getWriter().write("403 Forbidden: Timestamp Expired");
                return false;
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("403 Forbidden: Invalid Timestamp");
            return false;
        }

        return true;
    }
}