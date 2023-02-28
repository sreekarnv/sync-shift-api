package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.dto.*;
import com.example.timingconsensusscheduler.entity.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "576D5A7134743777217A25432A462D4A614E645267556B58703272357538782F";

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails details) {
        return generateToken(new HashMap<>(), details);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails details) {
        var _details = (User) details;
        var payload = new JwtPayloadDto(
                _details.getId(),
                _details.getName(),
                _details.getEmail(),
                _details.getRole()
        );
        extraClaims.put("user", payload);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails details) {
        final String email = extractEmail(token);
        return email.equals(details.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
