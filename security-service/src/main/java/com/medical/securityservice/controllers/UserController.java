package com.medical.securityservice.controllers;

import com.medical.securityservice.models.dto.UserLoginDto;
import com.medical.securityservice.models.dto.UserRegisterDto;
import com.medical.securityservice.models.dto.UserResponseLoginDto;
import com.medical.securityservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponseLoginDto> login(@Valid @RequestBody UserLoginDto userLogin){
        return new ResponseEntity<>(userService.login(userLogin), HttpStatus.OK);
    }
    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegisterDto userRegister) {
        if(userService.register(userRegister))
            return new ResponseEntity<>(
                    Collections.singletonMap("message", "user registered"),
                    HttpStatus.CREATED
            );
        else
            return new ResponseEntity<>(
                    Collections.singletonMap("message", "user not registered"),
                    HttpStatus.NOT_ACCEPTABLE
            );
    }
    

}
