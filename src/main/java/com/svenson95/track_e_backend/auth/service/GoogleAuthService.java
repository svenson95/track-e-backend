package com.svenson95.track_e_backend.auth.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.repository.UserRepository;

@Service
public class GoogleAuthService {

    @Value("${GOOGLE_CLIENT_ID:}")
    private String CLIENT_ID;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> verifyToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    new GsonFactory()
            ).setAudience(Collections.singletonList(CLIENT_ID))
             .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                this.registerUser(payload);

                return Map.of(
                        "userId", payload.getSubject(),
                        "email", payload.getEmail(),
                        "name", (String) payload.get("name"),
                        "picture", (String) payload.get("picture")
                );
            } else {
                return null;
            }
        } catch (IOException | GeneralSecurityException e) {
            return null;
        }
    }

    private void registerUser(GoogleIdToken.Payload payload) {
        String userId = payload.getSubject();

        if (!userRepository.existsByGoogleId(userId)) {
            User user = new User();

            user.setGoogleId(userId);
            user.setEmail(payload.getEmail());
            user.setName((String) payload.get("name"));
            user.setPicture((String) payload.get("picture"));
            user.setWeight(0);
            user.setHeight(0);

            userRepository.save(user);
        }
    }
}
