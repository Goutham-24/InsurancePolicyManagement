package com.project.creation.JWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
public class JwtUtil {

    private static final String SECRET_KEY = "ThisIsASecretKeyForJwtSigningDontShare123456"; 
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username, String role) {
        System.out.println("Generating token for: " + username);
        try{
            String check = Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
        System.out.println(">>> Generated Token: " + check);    
        return check;
        }
        catch(Throwable t){
            System.out.println(">>> ERROR in generateToken: " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
            throw t;
        }
        finally {
        System.out.println(">>> FINALLY reached in generateToken");
    }
            
    }

    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public static String extractRole(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class);
    }
}

