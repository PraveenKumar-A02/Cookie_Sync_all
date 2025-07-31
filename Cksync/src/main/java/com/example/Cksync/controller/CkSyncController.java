package com.example.Cksync.controller;

import com.example.Cksync.service.CookieSyncService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cksync")
public class CkSyncController {

    @Autowired
    private CookieSyncService service;

    @GetMapping("/sync")
    public void sync(@RequestParam("mro_cookie_id") String mroCookieId, @CookieValue(value = "mro_sync_id", required = false) String mroSyncId, HttpServletResponse res) {
        if (mroSyncId == null) {
            mroSyncId = service.sync(mroCookieId);
            Cookie syncCookie = new Cookie("mro_sync_id", mroSyncId);
            syncCookie.setPath("/");
            syncCookie.setMaxAge(60 * 60 * 24 * 365);
            res.addCookie(syncCookie);
        }

        service.handleVisit(mroCookieId);
        String redirect = "http://localhost:8080/mro/sspcall?redirect=http://localhost:8083/cksync/callback";
        res.setStatus(302);
        res.setHeader("Location", redirect);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> receiveCallback(@CookieValue("mro_sync_id") String syncId,
                                                  @RequestParam(required = false) String adx_id,
                                                  @RequestParam(required = false) String fluct_id) {
        if (adx_id != null) {
            service.adxCookie(syncId, adx_id);
        }
        if (fluct_id != null) {
            service.fluctCookie(syncId, fluct_id);
        }
        return ResponseEntity.ok("Success");
    }
}