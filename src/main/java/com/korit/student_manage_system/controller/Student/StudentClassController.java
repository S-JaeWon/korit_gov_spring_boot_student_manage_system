package com.korit.student_manage_system.controller.Student;

import com.korit.student_manage_system.dto.Request.JoinClassReqDto;
import com.korit.student_manage_system.security.model.Principal;
import com.korit.student_manage_system.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/std/class")
public class StudentClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/join")
    public ResponseEntity<?> joinClass(
            @RequestBody JoinClassReqDto joinClassReqDto,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(classService.joinClass(joinClassReqDto, principal));
    }

    @GetMapping("/getClassList")
    public ResponseEntity<?> getClassList(@AuthenticationPrincipal Principal principal) {
        return ResponseEntity.ok(classService.findClassListByUserId(principal));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelClass(
            @RequestParam String className,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(classService.cancelClass(className, principal));
    }

}
