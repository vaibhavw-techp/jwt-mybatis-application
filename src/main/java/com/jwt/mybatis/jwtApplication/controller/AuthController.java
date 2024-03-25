package com.jwt.mybatis.jwtApplication.controller;

import com.jwt.mybatis.jwtApplication.service.UserDetailService;
import com.jwt.mybatis.jwtApplication.dto.JwtRequest;
import com.jwt.mybatis.jwtApplication.dto.JwtResponse;
import com.jwt.mybatis.jwtApplication.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public JwtResponse getLogin(@RequestBody JwtRequest request) {
        try {
            this.doAuthenticate(request.getEmail(), request.getPassword());

            UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
            String token = this.helper.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
            return response;
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException(handleBadCredentialsException());
        }
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        manager.authenticate(authentication);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleBadCredentialsException() {
        return "Invalid Username or Password";
    }

}

