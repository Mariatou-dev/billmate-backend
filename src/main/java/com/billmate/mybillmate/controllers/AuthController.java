package com.billmate.mybillmate.controllers;

import com.billmate.mybillmate.config.JwtUtil;
import com.billmate.mybillmate.dtos.LoginRequest;
import com.billmate.mybillmate.dtos.LoginResponse;
import com.billmate.mybillmate.models.User;
import com.billmate.mybillmate.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // set to false for local testing if needed
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1 hour
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponse("Login successful", user.getUsername(), user.getId()));

//        Optional<User> optional = this.authService.findByCredentials(loginRequest.getUsername(), loginRequest.getPassword());
//        if (!optional.isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
//        httpSession.setAttribute("user", optional.get());
//        return ResponseEntity.ok(optional.get());
    }

//        @PostMapping
//        public LoginResponse authentication(@RequestBody LoginRequest request){
//            return userService.authenticate(request);
//        }

}

