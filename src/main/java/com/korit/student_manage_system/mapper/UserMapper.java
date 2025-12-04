package com.korit.student_manage_system.mapper;

import com.korit.student_manage_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> foundByUserId(Integer userId);
    Optional<User> foundByUsername(String username);
    int addUser(User user);
    int changePassword(User user);
    int removeStudent(Integer userId);
}
