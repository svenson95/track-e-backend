package com.svenson95.track_e_backend.auth.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "SUPER_SECRET_KEY"; // TODO: read from config
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 days

    public String generateToken(Map<String, Object> userInfo) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(userInfo.get("email").toString())
                .setClaims(userInfo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}