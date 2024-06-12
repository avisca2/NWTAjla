package com.medical.securityservice.services;

import com.medical.securityservice.models.dto.UserDto;
import com.medical.securityservice.models.dto.UserLoginDto;
import com.medical.securityservice.models.dto.UserRegisterDto;
import com.medical.securityservice.models.dto.UserResponseLoginDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserResponseLoginDto login(UserLoginDto userLogin);
    public boolean register(UserRegisterDto userRegister);
    public UserDto getUser();
}
