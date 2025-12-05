package com.korit.student_manage_system.repository;

import com.korit.student_manage_system.entity.Class;
import com.korit.student_manage_system.entity.UserClass;
import com.korit.student_manage_system.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassRepository {

    @Autowired
    private ClassMapper classMapper;

    public Optional<Class> foundByClassId(Integer classId) {
        return classMapper.foundByClassId(classId);
    }
    public Optional<Class> foundByClassName(String className) {
        return classMapper.foundByClassName(className);
    }

    public List<Class> foundClassListAll() {
        return classMapper.foundClassListAll();
    }

    public List<Class> findClassListByUserId(Integer userId) {
        return classMapper.findClassListByUserId(userId);
    }

    public int addClass(Class aClass) {
        return classMapper.addClass(aClass);
    }

    public int removeClass(String className) {
        return classMapper.removeClass(className);
    }
}
