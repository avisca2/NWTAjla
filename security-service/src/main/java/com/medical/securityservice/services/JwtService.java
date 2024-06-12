package com.medical.securityservice.services;

//import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
// import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.ExpirationTime}")
    private Long jwtExpirationTime;
    private String token;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    @SuppressWarnings("deprecation")
    public Claims extractAllClaims(String token) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(jwtSecret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    @SuppressWarnings("deprecation")
    public String createToken(Map<String, Object> claims, UserDetails userDetails) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(jwtSecret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .claim("role", userDetails.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .signWith(secretKey).compact();
    }
    @SuppressWarnings("deprecation")
    public String extractUserRoleFromToken() {
        byte[] secretKeyBytes = Base64.getEncoder().encode(jwtSecret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        Claims claims = ((JwtParser) Jwts.parser()
                .setSigningKey(secretKey))
                .parseClaimsJws(getToken())
                .getBody();
        return (String) claims.get("role");
    }

    // public Key getSignKey() {
    //     byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    //     return Keys.hmacShaKeyFor(keyBytes);
    // }
}