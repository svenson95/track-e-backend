package com.svenson95.track_e_backend.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svenson95.track_e_backend.auth.service.GoogleAuthService;
import com.svenson95.track_e_backend.auth.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GoogleAuthService googleAuthService;
    private final JwtService jwtService;

    public AuthController(GoogleAuthService googleAuthService, JwtService jwtService) {
        this.googleAuthService = googleAuthService;
        this.jwtService = jwtService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Map<String, String> body) {
        String idToken = body.get("token");

        Map<String, Object> userInfo = googleAuthService.verifyToken(idToken);
        if (userInfo == null) {
            return ResponseEntity.status(401).body("Invalid Google Token");
        }

        String jwt = jwtService.generateToken(userInfo);

        return ResponseEntity.ok(Map.of(
                "token", jwt,
                "user", userInfo
        ));
    }
}
