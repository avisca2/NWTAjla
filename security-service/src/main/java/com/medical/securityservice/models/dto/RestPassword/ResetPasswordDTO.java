package com.medical.securityservice.models.dto.RestPassword;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetPasswordDTO {
    private String email;
}
