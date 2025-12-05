package com.korit.student_manage_system.mapper;

import com.korit.student_manage_system.entity.UserClass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserClassMapper {
    int addUserClass(UserClass userClass);
    int deleteUserClass(Integer userId, Integer classId);

}
