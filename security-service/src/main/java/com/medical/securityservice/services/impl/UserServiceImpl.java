package com.medical.securityservice.services.impl;

import com.medical.securityservice.exceptions.ResourceNotFoundException;
import com.medical.securityservice.exceptions.UnAuthorizedException;
import com.medical.securityservice.exceptions.UniqueException;
import com.medical.securityservice.models.dto.UserDto;
import com.medical.securityservice.models.dto.UserLoginDto;
import com.medical.securityservice.models.dto.UserRegisterDto;
import com.medical.securityservice.models.dto.UserResponseLoginDto;
import com.medical.securityservice.models.entities.User;
import com.medical.securityservice.models.enums.Role;
import com.medical.securityservice.repositories.UserRepository;
import com.medical.securityservice.services.JwtService;
import com.medical.securityservice.services.UserService;
import com.medical.securityservice.utils.SecurityUtils;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Override
    public UserResponseLoginDto login(UserLoginDto userLogin){
        var user = loadUserByUsername(userLogin.getUsername());
        if(passwordEncoder.matches(userLogin.getPassword(), user.getPassword()))
            return UserResponseLoginDto.builder().access_token(
                    jwtService.generateToken(user)
            ).role(user.getAuthorities().toString()).build();
        else
            throw new UnAuthorizedException("wrong password or username");
    }

    @Override
    public boolean register(UserRegisterDto userRegister) {
        try{
            var user = modelMapper.map(userRegister, com.medical.securityservice.models.entities.User.class);
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
            return userRepository.save(user) != null ? true : false;
        }catch(DataIntegrityViolationException e){
            throw new UniqueException("username already exits");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.get().getUsername())
                    .password(user.get().getPassword())
                    .roles(String.valueOf(user.get().getRole().name()))
                    .build();
        else
            throw new UsernameNotFoundException("username doesn't exist");
    }

    @Override
    public UserDto getUser() {
        var user = userRepository.findByUsername(SecurityUtils.getName()).orElseThrow(()->new ResourceNotFoundException("user doesn't exist"));
        return modelMapper.map(user, UserDto.class);
    }
}
