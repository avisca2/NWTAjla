package com.etf.gateway.Utils;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String token;
    public boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;
        Date expiration = null;
        byte[] secretKeyBytes = Base64.getEncoder().encode(token.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        
        JwtParser jwtParser = Jwts.parser()
        		.verifyWith(secretKey)
        		.build();
        
        try {
            Jwt<?, Claims> parsedToken = (Jwt<?, Claims>) jwtParser.parseSignedClaims(jwt);
            subject = parsedToken.getPayload().getSubject();
            expiration = parsedToken.getPayload().getExpiration();
        
        } catch (Exception ex) {
        	returnValue = false;
        }
        
        if (subject == null || subject.isEmpty()) {
        	returnValue = false;
        }

        if(expiration != null && expiration.before(new Date()))
            returnValue = false;
        
        return returnValue;
    }
}
