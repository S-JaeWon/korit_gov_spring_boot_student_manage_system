package com.korit.student_manage_system.dto.Request;

import com.korit.student_manage_system.entity.UserClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinClassReqDto {
    private String className;

    public UserClass toEntity(Integer userId, Integer classId) {
        return UserClass .builder()
                .userId(userId)
                .classId(classId)
                .build();
    }
}
