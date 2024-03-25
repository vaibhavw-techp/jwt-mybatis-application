package com.jwt.mybatis.jwtApplication.repository;

import com.jwt.mybatis.jwtApplication.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserRepository {
    UserEntity findUserByUsername(String username);
}
