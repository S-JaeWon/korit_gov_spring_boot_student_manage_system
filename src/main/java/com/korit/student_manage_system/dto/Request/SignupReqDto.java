package com.korit.student_manage_system.dto.Request;

import com.korit.student_manage_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupReqDto {
    private String username;
    private String password;
    private Integer age;

    public User toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .age(age)
                .build();
    }
}
