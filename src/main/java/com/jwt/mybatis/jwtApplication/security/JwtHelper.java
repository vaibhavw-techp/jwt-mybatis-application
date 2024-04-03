package com.jwt.mybatis.jwtApplication.security;

import com.jwt.mybatis.jwtApplication.entity.UserEntity;
import com.jwt.mybatis.jwtApplication.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String secret = "0ddf5597e02d981f8803c4cc11f015a4e52679d706edb29b595d9e466def5bcf95273a3053ab5d97ee893c23e4023b912daefaade316406a33b7685d4d223dfa";

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
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
