package com.korit.student_manage_system.service;

import com.korit.student_manage_system.dto.Request.ChangePdwReqDto;
import com.korit.student_manage_system.dto.Response.ApiRespDto;
import com.korit.student_manage_system.entity.User;
import com.korit.student_manage_system.repository.UserRepository;
import com.korit.student_manage_system.security.model.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApiRespDto<?> changeStdPassword(ChangePdwReqDto changePdwReqDto, Principal principal) {
        Integer userId = principal.getUserId();

        Optional<User> foundUser = userRepository.foundByUserId(userId);

        if (foundUser.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("존재하지 않은 사용자입니다.")
                    .build();
        }

        if (!bCryptPasswordEncoder.matches(changePdwReqDto.getOldPassword(), foundUser.get().getPassword())) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("기존 비밀번호와 일치 하지 않습니다.")
                    .build();
        }

        if (bCryptPasswordEncoder.matches(changePdwReqDto.getNewPassword(), foundUser.get().getPassword())) {
                    return ApiRespDto.builder()
                            .status("failed")
                            .message("이미 사용 중인 비밀번호 입니다.")
                            .build();
        }

        int result = userRepository.changePassword(changePdwReqDto.toEntity(userId, bCryptPasswordEncoder));

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("비밀번호 수정 완료")
                .build();
    }

    public ApiRespDto<?> removeStd(Integer userId, Principal principal) {
        boolean isAdmin = principal.getUserRoles().stream()
                .anyMatch(userRole -> userRole.getRoleId().equals(1));

        if (!isAdmin) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("삭제 권한이 없습니다.")
                    .build();
        }

        Optional<User> foundUser = userRepository.foundByUserId(userId);

        if (foundUser.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 User가 존재하지 않습니다.")
                    .build();
        }

        boolean isStudent = foundUser.get().getUserRoles().stream()
                .anyMatch(userRole -> userRole.getRoleId().equals(2));

        if (!isStudent) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("학생이 아닙니다.")
                    .build();
        }

        int result = userRepository.removeStudent(foundUser.get().getUserId());

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("user 삭제 완료")
                .build();
    }
}
