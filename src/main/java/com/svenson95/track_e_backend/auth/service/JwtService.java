package com.svenson95.track_e_backend.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET_KEY = System.getenv("JWT_SECRET");
  private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 days

  public String generateToken(Map<String, Object> userInfo) {
    return Jwts.builder()
        .setSubject(userInfo.get("email").toString())
        .setClaims(userInfo)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
        .compact();
  }
}
