package com.medical.securityservice.services;

public interface ResetPasswordService {
    public String createRestPassword(String email);
    public String activateAccount(String token, String newPassword);
    public boolean checkToken(String token);
}
