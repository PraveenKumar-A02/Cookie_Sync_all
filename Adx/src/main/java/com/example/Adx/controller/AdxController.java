package com.example.Adx.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/adx")
public class AdxController {

    @GetMapping("/sync")
    public void sspRedirect(@RequestParam String redirect, @RequestParam String fredirect, @CookieValue(value = "adx_id", required = false) String adxId, HttpServletResponse response) {
        if (adxId == null) {
            adxId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("adx_id", adxId);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        String separator = redirect.contains("?") ? "&" : "?";
        String finalRedirect = redirect + separator + "fredirect=" + fredirect + "&adx_id=" + adxId;

        response.setStatus(302);
        response.setHeader("Location", finalRedirect);
    }
}