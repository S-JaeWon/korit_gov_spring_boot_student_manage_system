package com.korit.student_manage_system.controller.Student;

import com.korit.student_manage_system.dto.Request.ChangePdwReqDto;
import com.korit.student_manage_system.security.model.Principal;
import com.korit.student_manage_system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/account")
public class StudentAccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/changePdw")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePdwReqDto changePdwReqDto,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(accountService.changeStdPassword(changePdwReqDto, principal));
    }
}
