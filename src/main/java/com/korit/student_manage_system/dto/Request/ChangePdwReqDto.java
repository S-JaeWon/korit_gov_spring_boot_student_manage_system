package com.korit.student_manage_system.dto.Request;

import com.korit.student_manage_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePdwReqDto {
    private Integer userId;
    private String oldPassword;
    private String newPassword;

    public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(newPassword))
                .build();
    }
}
