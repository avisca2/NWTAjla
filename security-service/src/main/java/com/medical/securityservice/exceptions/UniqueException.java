package com.medical.securityservice.exceptions;

public class UniqueException extends RuntimeException{
    UniqueException(){
        super();
    }
    public UniqueException(String msg){
        super(msg);
    }
}
