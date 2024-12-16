package com.zero.anti.hotlinking.controller;

import com.zero.anti.hotlinking.common.filter.TimeValidator;
import com.zero.anti.hotlinking.common.filter.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @GetMapping("/image")
    public String getImage(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = false) String token,
                           @RequestParam(required = false) String timestamp) throws IOException {

        // 验证 Token
        if (!TokenValidator.validateToken(request, response)) {
            return null;
        }

        // 验证时间戳
        if (!TimeValidator.validateTimestamp(request, response)) {
            return null;
        }

        return "Access granted to image resource";
    }
}