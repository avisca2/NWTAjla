package com.medical.securityservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "surname required")
    private String surname;
    @NotBlank(message = "phone required")
    private String phone;
    @NotNull(message = "birthday required")
    private LocalDate birthday;
    @NotBlank(message = "role required")
    private String role;
}
