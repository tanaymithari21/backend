package com.mangaverse.backend.controller;

import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "*")
public class CloudinaryController {

    // ⚠️ MOVE THESE TO application.properties IN REAL PROJECT
    private final String API_KEY = "347182478282281";
    private final String API_SECRET = "-pNyg-JSTjRu1fgJ0-IwT7QPiGc";

    @GetMapping("/signature")
    public Map<String, String> getSignature(@RequestParam String public_id) {

        long timestamp = System.currentTimeMillis() / 1000;

        // ✅ ONLY timestamp (IMPORTANT)
        // String publicId = request.getParameter("public_id");

        String toSign = "public_id=" + public_id + "&timestamp=" + timestamp;

        String signature = sha1(toSign + API_SECRET);

        Map<String, String> response = new HashMap<>();
        response.put("timestamp", String.valueOf(timestamp));
        response.put("signature", signature);
        response.put("api_key", API_KEY);

        System.out.println("STRING TO SIGN: " + toSign);
        System.out.println("SIGNATURE: " + signature);

        return response;
    }

    private String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8)); // ✅ FIX

            StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error generating SHA1", e);
        }
    }
}