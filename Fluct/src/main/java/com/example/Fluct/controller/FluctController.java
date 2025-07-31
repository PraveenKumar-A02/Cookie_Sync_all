package com.example.Fluct.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fluct")
public class FluctController {

    @GetMapping("/sync")
    public void sspRedirect(@RequestParam String redirect, @RequestParam String fredirect, @CookieValue(value = "fluct_id", required = false) String fluctId, HttpServletResponse response) {
        if (fluctId == null) {
            fluctId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("fluct_id", fluctId);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        String separator = redirect.contains("?") ? "&" : "?";
        String finalRedirect = redirect + separator + "fredirect=" + fredirect + "&fluct_id=" + fluctId;

        response.setStatus(302);
        response.setHeader("Location", finalRedirect);
    }
}