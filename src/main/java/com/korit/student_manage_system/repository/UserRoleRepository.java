package com.korit.student_manage_system.repository;

import com.korit.student_manage_system.entity.UserRole;
import com.korit.student_manage_system.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public void addUserRole(UserRole userRole) {
        userRoleMapper.addUserRole(userRole);
    }

    public void updateUserRole(UserRole userRole) {
        userRoleMapper.updateUserRole(userRole);
    }
}
