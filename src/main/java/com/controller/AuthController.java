package com.controller;

import com.entities.User;
import com.response.BaseResponse;
import com.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/register")
    public BaseResponse register(@RequestBody User user) {
        return authService.register(user);
    }

}
