package com.medical.securityservice.services.impl;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medical.securityservice.exceptions.EmailExcpetion;
import com.medical.securityservice.exceptions.ResetPasswordException;
import com.medical.securityservice.exceptions.ResourceNotFoundException;
import com.medical.securityservice.repositories.ResetPasswordRepository;
import com.medical.securityservice.repositories.UserRepository;
import com.medical.securityservice.services.EmailService;
import com.medical.securityservice.services.ResetPasswordService;
import com.medical.securityservice.services.UserService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService{
    private final ResetPasswordRepository resetPasswordRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ResetPasswordServiceImpl(
        ResetPasswordRepository resetPasswordRepository,
        EmailService emailService,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ){
        this.resetPasswordRepository = resetPasswordRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String createRestPassword(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(()->new EmailExcpetion("email not found"));
        if(emailService.sendReset(user))
            return "reset password sent";
        else
            throw new EmailExcpetion("error while sending email or the activation link is already sent");
    }
    @Override
    public String activateAccount(String token, String newPassword) {
        var resetPassword = resetPasswordRepository.findByToken(token).orElseThrow(()->new ResetPasswordException("token invalid"));
        // if(checkExpiration(resetPassword.getExpired())){
            resetPassword.getUser().setPassword(
                    passwordEncoder.encode(newPassword)
            );
            userRepository.save(
                resetPassword.getUser()
            );
            resetPasswordRepository.deleteById(resetPassword.getId());
            return "password changed";
        // }else{
        //     throw new ResetPasswordException("error during reset password");
        // }
    }
    @Override
    public boolean checkToken(String token) {
        resetPasswordRepository.findByToken(token).orElseThrow(()->new ResetPasswordException("token invalid"));
        // return checkExpiration(resetPassword.getExpired());
        return true;
    }

    private boolean checkExpiration(Date date){
        return date.after(new Date());
    }
}
