package com.jwt.mybatis.jwtApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String role;
}