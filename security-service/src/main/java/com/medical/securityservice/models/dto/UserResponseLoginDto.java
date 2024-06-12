package com.medical.securityservice.models.dto;


import lombok.*;

/**
 * userResponseLoginDTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseLoginDto {
    private String access_token;
    private String role;
}
