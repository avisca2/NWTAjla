package com.medical.securityservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailExcpetion extends RuntimeException{
    public EmailExcpetion(String msg){
        super(msg);
    }
}
