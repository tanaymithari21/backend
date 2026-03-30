package com.mangaverse.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "*")
public class CloudinaryController {

    @Value("${cloudinary.api-key}")
    private String API_KEY;

    @Value("${cloudinary.api-secret}")
    private String API_SECRET;

    @GetMapping("/signature")
    public Map<String, String> getSignature(@RequestParam String public_id) {
        long timestamp = System.currentTimeMillis() / 1000;
        String toSign = "public_id=" + public_id + "&timestamp=" + timestamp;
        String signature = sha1(toSign + API_SECRET);
        Map<String, String> res = new HashMap<>();
        res.put("timestamp", String.valueOf(timestamp));
        res.put("signature", signature);
        res.put("api_key", API_KEY);
        return res;
    }

    // Used by frontend to get a signed delete token for a Cloudinary image
    @GetMapping("/delete-signature")
    public Map<String, String> getDeleteSignature(@RequestParam String public_id) {
        long timestamp = System.currentTimeMillis() / 1000;
        String toSign = "public_id=" + public_id + "&timestamp=" + timestamp;
        String signature = sha1(toSign + API_SECRET);
        Map<String, String> res = new HashMap<>();
        res.put("timestamp", String.valueOf(timestamp));
        res.put("signature", signature);
        res.put("api_key", API_KEY);
        res.put("public_id", public_id);
        return res;
    }

    private String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating SHA1", e);
        }
    }
}
