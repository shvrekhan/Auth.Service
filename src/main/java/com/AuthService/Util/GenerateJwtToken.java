package com.AuthService.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class GenerateJwtToken {

    public String getToken(String name, String email) {
        long expirationTimeInMilliSecond = System.currentTimeMillis() + 3600 * 1000;
        String subject = "name: " + name + " \n" + "email: " + email;
        return Jwts.builder()
                .subject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationTimeInMilliSecond))
                .signWith(SignatureAlgorithm.HS256, "5234523523452345")
                .compact();
    }
}
