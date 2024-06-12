package com.medical.securityservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResetPasswordException extends RuntimeException{
    public ResetPasswordException(String msg){
        super(msg);
    }
}
