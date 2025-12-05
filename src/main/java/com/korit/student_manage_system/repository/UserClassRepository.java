package com.korit.student_manage_system.repository;

import com.korit.student_manage_system.entity.UserClass;
import com.korit.student_manage_system.mapper.UserClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserClassRepository {

    @Autowired
    private UserClassMapper userClassMapper;

    public int addUserClass(UserClass userClass) {
        return userClassMapper.addUserClass(userClass);
    }

    public int deleteUserClass(Integer userId, Integer classId) {
        return userClassMapper.deleteUserClass(userId, classId);
    }
}
