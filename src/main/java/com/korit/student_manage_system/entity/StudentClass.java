package com.korit.student_manage_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentClass {
    private Integer studentClassId;
    private Integer userId;
    private Integer classId;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
