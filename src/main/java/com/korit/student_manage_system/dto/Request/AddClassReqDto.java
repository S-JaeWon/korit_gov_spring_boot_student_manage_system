package com.korit.student_manage_system.dto.Request;

import com.korit.student_manage_system.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddClassReqDto {
    private String className;
    private String classDescription;

    public Class toEntity() {
        return Class.builder()
                .className(className)
                .classDescription(classDescription)
                .build();
    }
}
