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
        if (!changePdwReqDto.getUserId().equals(principal.getUserId())) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("잘못된 접근입니다.")
                    .build();
        }

        return null;
    }
}
