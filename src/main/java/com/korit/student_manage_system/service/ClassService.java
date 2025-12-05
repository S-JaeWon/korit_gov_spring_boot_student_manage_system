package com.korit.student_manage_system.service;

import com.korit.student_manage_system.dto.Request.AddClassReqDto;
import com.korit.student_manage_system.dto.Request.JoinClassReqDto;
import com.korit.student_manage_system.dto.Response.ApiRespDto;
import com.korit.student_manage_system.entity.Class;
import com.korit.student_manage_system.repository.ClassRepository;
import com.korit.student_manage_system.repository.UserClassRepository;
import com.korit.student_manage_system.security.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserClassRepository userClassRepository;

    public ApiRespDto<?> addClass(AddClassReqDto addClassReqDto, Principal principal) {
        boolean isAdmin = principal.getUserRoles().stream()
                .anyMatch(userRole -> userRole.getRoleId().equals(1));

        if (!isAdmin) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("권한이 없습니다.")
                    .build();
        }

        Optional<Class> foundClass = classRepository.foundByClassName(addClassReqDto.getClassName());

        if (foundClass.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("이미 존재하는 Class 입니다.")
                    .build();
        }

        int result = classRepository.addClass(addClassReqDto.toEntity());

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("class 추가 완료")
                .data(addClassReqDto)
                .build();
    }

    public ApiRespDto<?> foundClassListAll(Principal principal) {
        boolean isAdmin = principal.getUserRoles().stream()
                .anyMatch(userRole -> userRole.getRoleId().equals(1));

        if (!isAdmin) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("권한이 없습니다.")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("조회 완료")
                .data(classRepository.foundClassListAll())
                .build();
    }

    public ApiRespDto<?> removeClass(String className, Principal principal) {
        boolean isAdmin = principal.getUserRoles().stream()
                .anyMatch(userRole -> userRole.getRoleId().equals(1));

        if (!isAdmin) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("권한이 없습니다.")
                    .build();
        }

        Optional<Class> foundClass = classRepository.foundByClassName(className);

        if (foundClass.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 과목은 존재하지 않습니다.")
                    .build();
        }

        classRepository.removeClass(className);

        return ApiRespDto.builder()
                .status("success")
                .message("삭제 완료")
                .build();
    }

    public ApiRespDto<?> findClassListByUserId(Principal principal) {
        Integer userId = principal.getUserId();

        List<Class> classList = classRepository.findClassListByUserId(userId);

        List<String> classNameList = classList.stream()
                .map(Class::getClassName)
                .toList();

        return ApiRespDto.builder()
                .status("success")
                .message("조회 완료")
                .data(classNameList)
                .build();
    }

    public ApiRespDto<?> joinClass(JoinClassReqDto joinClassReqDto, Principal principal) {
        Integer userId = principal.getUserId();

        boolean isStudent = principal.getUserRoles().stream()
                .anyMatch(role -> role.getRoleId().equals(2));
        if (!isStudent) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("학생만 수강 신청이 가능합니다.")
                    .build();
        }

        Optional<Class> foundClass = classRepository.foundByClassName(joinClassReqDto.getClassName());

        if (foundClass.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("존재 하지 않은 class 입니다.")
                    .build();
        }

        Class aClass = foundClass.get();
        Integer classId = aClass.getClassId();

        int result = userClassRepository.addUserClass(joinClassReqDto.toEntity(userId, classId));

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("수강 신청 완료")
                .build();
    }

    public ApiRespDto<?> cancelClass(String className, Principal principal) {
        Integer userId = principal.getUserId();

        boolean isStudent = principal.getUserRoles().stream()
                .anyMatch(role -> role.getRoleId().equals(2));
        if (!isStudent) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("학생 계정으로 로그인 해주세요.")
                    .build();
        }

        Optional<Class> foundClass = classRepository.foundByClassName(className);

        Integer classId = foundClass.get().getClassId();

        int result = userClassRepository.deleteUserClass(userId, classId);

        if (result != 1) {  // 삭제된 row가 0이면 (수강 중이 아니었을 때)
            return ApiRespDto.builder()
                    .status("failed")
                    .message("오류가 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message(className + " 수강 취소 완료")
                .build();
    }
}
