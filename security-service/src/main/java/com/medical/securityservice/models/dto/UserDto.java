package com.medical.securityservice.models.dto;

import com.medical.securityservice.models.enums.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
