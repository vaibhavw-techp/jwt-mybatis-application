package com.jwt.mybatis.jwtApplication.security;

import com.jwt.mybatis.jwtApplication.entity.UserEntity;
import com.jwt.mybatis.jwtApplication.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 30 * 60; // 30 minutes

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        UserEntity role = userRepository.findUserByUsername(userDetails.getUsername());
        claims.put("Role", role.getRole());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
