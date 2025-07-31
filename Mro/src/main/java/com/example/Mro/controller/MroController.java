package com.example.Mro.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mro")
public class MroController {

    @GetMapping("/visit")
    public String handleVisit(@CookieValue(value = "mro_cookie_id", required = false) String mroCookieId, HttpServletResponse res) {
        if (mroCookieId == null) {
            mroCookieId = "mro_" + UUID.randomUUID().toString();
            Cookie c = new Cookie("mro_cookie_id", mroCookieId);
            c.setPath("/");
            c.setMaxAge(60 * 60 * 24 * 365);
            res.addCookie(c);
        }

        String redirect = "http://localhost:8083/cksync/sync?mro_cookie_id=" + mroCookieId;
        res.setStatus(302);
        res.setHeader("Location", redirect);
        return "";
    }

    @GetMapping("/sspcall")
    public void sspcall(HttpServletResponse res, @RequestParam("redirect") String redirect) {
        String redirect1 = "http://localhost:8082/fluct/sync?redirect=http://localhost:8083/cksync/callback&fredirect=" + redirect;
        res.setStatus(302);
        res.setHeader("Location", redirect1);

        String redirect2 = "http://localhost:8081/adx/sync?redirect=http://localhost:8083/cksync/callback&fredirect=" + redirect;
        res.setStatus(302);
        res.setHeader("Location", redirect2);
    }

    @GetMapping("fluct/callback")
    public void callback(HttpServletResponse res, @RequestParam String fredirect, @RequestParam(required = false) String fluct_id) {
        String separator = fredirect.contains("?") ? "&" : "?";
        String redirect = fredirect + separator + "fluct_id=" + fluct_id;
        res.setStatus(302);
        res.setHeader("Location", redirect);
    }

    @GetMapping("adx/callback")
    public void adxcallback(HttpServletResponse res, @RequestParam String fredirect, @RequestParam(required = false) String adx_id) {
        String separator = fredirect.contains("?") ? "&" : "?";
        String redirect = fredirect + separator + "adx_id=" + adx_id;
        res.setStatus(302);
        res.setHeader("Location", redirect);
    }
}