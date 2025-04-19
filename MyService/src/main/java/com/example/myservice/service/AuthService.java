package com.example.myservice.service;

import com.example.myservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService {
    @Value("${progweb.security.private-key}")
    private String key;

    public Map<String, String> login(User user) {
        return Map.of();
//
//        Instant now = Instant.now();
//
//        Authentication auth = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        Jwts.builder()
//            .claim("username", user.getUsername())
//            .setSubject(user.getUsername())
//            .setIssuedAt(Date.from(now))
//            .setExpiration(Date.from(now.plus(10, ChronoUnit.DAYS)))
//            .setIssuer("projet_web")
//            .setId(UUID.randomUUID().toString())
//            .signWith(SignatureAlgorithm.HS256,key)
//        ;
//        String token = jwtUtil.generateToken(auth);
//        return Map.of("token", token);
    }

    @Value("${progweb.security.private-key}")
    public String key() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AuthService) obj;
        return Objects.equals(this.key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "AuthService[" +
                "key=" + key + ']';
    }

}
