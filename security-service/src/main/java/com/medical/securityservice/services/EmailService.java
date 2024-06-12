package com.medical.securityservice.services;

import com.medical.securityservice.models.entities.User;

public interface EmailService {
    public boolean sendReset(User user);
}
