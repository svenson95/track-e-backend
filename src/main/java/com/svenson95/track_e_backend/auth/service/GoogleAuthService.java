package com.svenson95.track_e_backend.auth.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleAuthService {

    private static final String CLIENT_ID = "81384485805-o4b55e424moljjf98egavlhol819l18a.apps.googleusercontent.com";

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
}
