package com.config.jwt;

import com.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final String JWT_SECRET = "test";


    private final long JWT_EXPIRATION = 604800000L;

    private ObjectMapper objectMapper = new ObjectMapper();

    public String generateToken(UserDto userDto) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
            String jsonObject = objectMapper.writeValueAsString(userDto);
            // Tạo chuỗi json web token từ id của user.
            return Jwts.builder()
                    .setSubject(jsonObject)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                    .compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDto getUserFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        String jsonObject = claims.getSubject();
        try {
            return objectMapper.readValue(jsonObject, UserDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
