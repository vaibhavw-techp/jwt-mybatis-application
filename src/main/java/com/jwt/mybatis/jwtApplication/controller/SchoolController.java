package com.jwt.mybatis.jwtApplication.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/students")
public class SchoolController {


    @GetMapping
    @RolesAllowed("STUDENT")
    public String getId(){
        return "10";
    }


    @GetMapping("user")
    @RolesAllowed("TEACHER")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

}
