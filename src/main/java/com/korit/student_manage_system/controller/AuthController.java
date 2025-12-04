package com.korit.student_manage_system.controller;

import com.korit.student_manage_system.dto.Request.SigninReqDto;
import com.korit.student_manage_system.dto.Request.SignupReqDto;
import com.korit.student_manage_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/student/signup")
    public ResponseEntity<?> stdSignup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(authService.stdSignup(signupReqDto));
    }
    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(authService.adminSignup(signupReqDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(authService.signin(signinReqDto));
    }
}
