package com.mybookstore.authservice.controller;

import com.mybookstore.authservice.model.UserEntity;
import com.mybookstore.authservice.repository.UserRepository;
import com.mybookstore.authservice.service.JwtService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ✅ CORS aktiviert für Angular
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        System.out.println("🔐 Login request for: " + request.getUsername());

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Benutzer nicht gefunden"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falsches Passwort");
        }

        String token = jwtService.generateToken(user.getUsername());

        AuthResponse response = new AuthResponse();
        response.setUsername(user.getUsername());
        response.setToken(token);
        response.setRole(user.getRole());

        return ResponseEntity.ok(response);
    }

    @Getter
    @Setter
    static class AuthRequest {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    static class AuthResponse {
        private String username;
        private String token;
        private String role;
    }
}
