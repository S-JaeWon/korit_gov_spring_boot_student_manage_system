package com.korit.student_manage_system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Class {
    private Integer classId;
    private String className;
    private String classDescription;
}
