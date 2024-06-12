package com.medical.securityservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.securityservice.models.dto.RestPassword.ActivateDTO;
import com.medical.securityservice.models.dto.RestPassword.ResetPasswordDTO;
import com.medical.securityservice.services.ResetPasswordService;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/reset-password")
public class ResetPasswordController {
    private final ResetPasswordService resetPasswordService;

    public ResetPasswordController(
        ResetPasswordService resetPasswordService
    ){
        this.resetPasswordService = resetPasswordService;
    }

    @GetMapping("/{token}")
    public ResponseEntity<Map<String, Boolean>> checkToken(@PathVariable String token){
        Map<String, Boolean> message = new HashMap<>();
        message.put("valide", resetPasswordService.checkToken(token));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("activate/{token}")
    public ResponseEntity<Map<String, String>> activateAccount(@PathVariable String token, @RequestBody ActivateDTO activateDTO){
        Map<String, String> message = new HashMap<>();
        message.put("message", resetPasswordService.activateAccount(token, activateDTO.getNewPassword()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("forgot")
    public ResponseEntity<Map<String, String>> sendReset(@RequestBody ResetPasswordDTO resetPasswordDTO){
        Map<String, String> message = new HashMap<>();
        message.put("message", resetPasswordService.createRestPassword(resetPasswordDTO.getEmail()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
