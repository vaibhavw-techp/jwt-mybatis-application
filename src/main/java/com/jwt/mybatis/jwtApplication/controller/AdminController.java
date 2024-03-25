package com.jwt.mybatis.jwtApplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdmin(){
        return "It is Admin API endpoint";
    }
}
