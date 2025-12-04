package com.korit.student_manage_system.service;

import com.korit.student_manage_system.dto.Request.SigninReqDto;
import com.korit.student_manage_system.dto.Request.SignupReqDto;
import com.korit.student_manage_system.dto.Response.ApiRespDto;
import com.korit.student_manage_system.entity.User;
import com.korit.student_manage_system.entity.UserRole;
import com.korit.student_manage_system.repository.UserRepository;
import com.korit.student_manage_system.repository.UserRoleRepository;
import com.korit.student_manage_system.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public ApiRespDto<?> stdSignup(SignupReqDto signupReqDto) {
        Optional<User> foundUser = userRepository.foundByUsername(signupReqDto.getUsername());
        if (foundUser.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("이미 존재하는 username 입니다.")
                    .build();
        }

        Optional<User> user = userRepository.addUser(signupReqDto.toEntity(bCryptPasswordEncoder));
        UserRole userRole = UserRole.builder()
                .userId(user.get().getUserId())
                .roleId(2)
                .build();
        userRoleRepository.addUserRole(userRole);

        return ApiRespDto.builder()
                .status("success")
                .message("회원가입 완료")
                .data(user.get())
                .build();
    }

    public ApiRespDto<?> adminSignup(SignupReqDto signupReqDto) {
        Optional<User> foundUser = userRepository.foundByUsername(signupReqDto.getUsername());
        if (foundUser.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("이미 존재하는 username 입니다.")
                    .build();
        }

        Optional<User> user = userRepository.addUser(signupReqDto.toEntity(bCryptPasswordEncoder));
        UserRole userRole = UserRole.builder()
                .userId(user.get().getUserId())
                .roleId(1)
                .build();
        userRoleRepository.addUserRole(userRole);

        return ApiRespDto.builder()
                .status("success")
                .message("회원가입 완료")
                .data(user.get())
                .build();
    }

    public ApiRespDto<?> signin(SigninReqDto signinReqDto) {
        Optional<User> foundUser = userRepository.foundByUsername(signinReqDto.getUsername());

        if (foundUser.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("사용자 정보를 다시 확인해주세요.")
                    .build();
        }

        User user = foundUser.get();

        if (!bCryptPasswordEncoder.matches(signinReqDto.getPassword(), user.getPassword())) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("사용자 정보를 다시 확인해주세요.")
                    .build();
        }

        String token = jwtUtils.generateAccessToken(user.getUserId().toString());

        return ApiRespDto.builder()
                .status("success")
                .message("로그인 성공")
                .data(token)
                .build();
    }
}
