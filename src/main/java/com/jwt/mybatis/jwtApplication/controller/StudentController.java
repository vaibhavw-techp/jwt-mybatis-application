package com.jwt.mybatis.jwtApplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getStudents(){
        return "It is Students API endpoint";
    }
}
