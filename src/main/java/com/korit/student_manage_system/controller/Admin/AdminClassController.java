package com.korit.student_manage_system.controller.Admin;

import com.korit.student_manage_system.dto.Request.AddClassReqDto;
import com.korit.student_manage_system.security.model.Principal;
import com.korit.student_manage_system.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/class")
public class AdminClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/add")
    public ResponseEntity<?> addClass(
            @RequestBody AddClassReqDto addClassReqDto,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(classService.addClass(addClassReqDto, principal));
    }

    @GetMapping("/getClassList")
    public ResponseEntity<?> getClass(@AuthenticationPrincipal Principal principal) {
        return ResponseEntity.ok(classService.foundClassListAll(principal));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> removeClass(
            @RequestParam String className,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(classService.removeClass(className, principal));
    }
}
