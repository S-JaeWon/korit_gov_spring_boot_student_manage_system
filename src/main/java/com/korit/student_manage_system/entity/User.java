package com.korit.student_manage_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer userId;
    private String username;
    @JsonIgnore
    private String password;
    private Integer age;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    private List<UserRole> userRoles;
}
