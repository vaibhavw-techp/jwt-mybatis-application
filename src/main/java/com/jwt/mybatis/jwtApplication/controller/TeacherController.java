package com.jwt.mybatis.jwtApplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public String getTeachers(){
        return "It is Teacher API endpoint";
    }
}
