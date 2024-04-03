package com.jwt.mybatis.jwtApplication.security;

import com.jwt.mybatis.jwtApplication.entity.UserEntity;
import com.jwt.mybatis.jwtApplication.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("classpath:application.properties")
public class JwtHelperNimbus {

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

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer("Vaibhav")
                .subject(subject)
                .claim("Role", claims.get("Role"))
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(Instant.now().plusSeconds(JWT_TOKEN_VALIDITY)))
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();

        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            signedJWT.sign(new MACSigner(keySpec));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return signedJWT.serialize();
    }
}
