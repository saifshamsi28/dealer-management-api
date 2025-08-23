package com.saif.dealer.controller;

import com.saif.dealer.config.JwtConfig;
import com.saif.dealer.dto.AuthRequest;
import com.saif.dealer.dto.AuthResponse;
import com.saif.dealer.dto.RegisterRequest;
import com.saif.dealer.entity.User;
import com.saif.dealer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtConfig.generateToken(userDetails);

        Optional<User> user = userService.findByUsername(authRequest.getUsername());

        if(user.isPresent()) {
            long expirationTime = System.currentTimeMillis() + jwtExpiration;
            return ResponseEntity.ok(new AuthResponse(jwt, user.get().getUsername(), user.get().getId(), expirationTime));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.createUser(registerRequest.getUsername(), registerRequest.getPassword());
            final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            final String jwt = jwtConfig.generateToken(userDetails);

            long expirationTime = System.currentTimeMillis() + jwtExpiration;
            return ResponseEntity.ok(new AuthResponse(jwt, user.getUsername(), user.getId(), expirationTime));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
