package com.korit.student_manage_system.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRespDto<T> {
    private String status;
    private String message;
    private T data;
}
