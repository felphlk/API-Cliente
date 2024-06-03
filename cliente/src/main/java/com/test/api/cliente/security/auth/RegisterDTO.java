package com.test.api.cliente.security.auth;

import com.test.api.cliente.security.auth.user.UserRole;

public record RegisterDTO(String login , String password, UserRole role){}
