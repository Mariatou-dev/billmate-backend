package com.billmate.mybillmate.controllers;

import com.billmate.mybillmate.dtos.LoginRequest;
import com.billmate.mybillmate.models.User;
import com.billmate.mybillmate.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession){
        Optional<User> optional = this.authService.findByCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        httpSession.setAttribute("user", optional.get());
        return ResponseEntity.ok(optional.get());
    }
}

