package com.korit.student_manage_system.repository;

import com.korit.student_manage_system.entity.User;
import com.korit.student_manage_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<User> foundByUserId(Integer userId) {
        return userMapper.foundByUserId(userId);
    }

    public Optional<User> foundByUsername(String username) {
        return userMapper.foundByUsername(username);
    }

    public Optional<User> addUser(User user) {
        try {
            userMapper.addUser(user);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public int changePassword(User user) {
        return userMapper.changePassword(user);
    }

    public int removeStudent(Integer userId) {
        return userMapper.removeStudent(userId);
    }
}
