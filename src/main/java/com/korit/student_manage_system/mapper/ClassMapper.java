package com.korit.student_manage_system.mapper;

import com.korit.student_manage_system.entity.Class;
import com.korit.student_manage_system.entity.UserClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ClassMapper {
    Optional<Class> foundByClassId(Integer classId);
    Optional<Class> foundByClassName(String className);
    List<Class> foundClassListAll();
    List<Class> findClassListByUserId(Integer userId);
    int addClass(Class aClass);
    int removeClass(String className);
}
