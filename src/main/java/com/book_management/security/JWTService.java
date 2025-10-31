package com.book_management.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


import javax.crypto.SecretKey;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {


	
    public String generateToken(String username) {
    	
       final String SECRET_KEY = "a2B4f6H8j0K2m4P6r8T0v2X4y6Z8b0C2d4E6g8I0k2M4p6R8t0V2x4Y6z8B0"; // Use strong key
     //  final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        
    	String token= Jwts.builder()
			                .setSubject(username)
			                .setIssuedAt(new Date())
			                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 mins expiry
			                .signWith(getSignInKey(SECRET_KEY), SignatureAlgorithm.HS256)
			                .compact();
    	return token;
    }
    
    private Key getSignInKey(String SECRET_KEY) {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
