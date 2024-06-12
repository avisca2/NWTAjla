package com.medical.securityservice.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.medical.securityservice.models.entities.ResetPassword;
import com.medical.securityservice.models.entities.User;
import com.medical.securityservice.repositories.ResetPasswordRepository;
import com.medical.securityservice.services.EmailService;

import java.util.Date;
import java.util.UUID;
import java.util.Calendar;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;
    private final ResetPasswordRepository resetPasswordRepository;

    public EmailServiceImpl(
        JavaMailSender emailSender,
        ResetPasswordRepository resetPasswordRepository
    ){
        this.emailSender = emailSender;
        this.resetPasswordRepository = resetPasswordRepository;
    }

    @Override
    public boolean sendReset(User user) {
        try{
            var resetPassword = resetPasswordRepository.save(ResetPassword.builder().user(user).token(UUID.randomUUID().toString()).build());
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom("aymanebel2@outlook.fr");
            message.setTo(user.getEmail()); 
            message.setSubject("Reset Password"); 
            message.setText(String.format("visit this link for password reset: http://localhost:5173/reset-password?token=%s", resetPassword.getToken()));
            emailSender.send(message);
            return true;
        }catch(Exception e){
            //throw new EmailExcpetion("error while send reset password");
            return false;
        }
    }

    private Date costumeDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 6*60*60);
        return calendar.getTime();
    }
    
}
