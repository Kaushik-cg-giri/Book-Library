package com.book_management.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


import javax.crypto.SecretKey;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWtUtil {

	// String SECRET_KEY=null;
	
    private final String SECRET = "my-super-secret-key-that-is-long-enough-1234567890!@#";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
    public String generateToken(String username) {
    	
    //    SECRET_KEY = "a2B4f6H8j0K2m4P6r8T0v2X4y6Z8b0C2d4E6g8I0k2M4p6R8t0V2x4Y6z8B0"; // Use strong key
     //  final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
     
    	String token= Jwts.builder()
			                .setSubject(username)
			                .setIssuedAt(new Date())
			                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 mins expiry
			                .signWith(key, SignatureAlgorithm.HS256)
			                .compact();
    	return token;
    }
    
 /*   private Key getSignInKey(String SECRET_KEY) {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/

	public String extractUsername(String token) {

		Claims body = extractClaims(token);
		return body.getSubject();
	}

	private Claims extractClaims(String token) {
		Claims body = Jwts.parserBuilder()
							.setSigningKey(key)
							.build()
							.parseClaimsJws(token)
							.getBody();
		return body;
	}

    public boolean validateToken(String username, UserDetails userDetails,String token) {
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
     }

     private boolean isTokenExpired(String token) {
         return extractClaims(token).getExpiration().before(new Date());
     }
}
