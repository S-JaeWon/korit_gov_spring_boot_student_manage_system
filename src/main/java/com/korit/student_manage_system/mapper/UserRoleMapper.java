package com.korit.student_manage_system.mapper;

import com.korit.student_manage_system.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    void addUserRole(UserRole userRole);
    void updateUserRole(UserRole userRole);
}
