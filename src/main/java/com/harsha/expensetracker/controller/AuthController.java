package com.harsha.expensetracker.controller;

import com.harsha.expensetracker.model.User;
import com.harsha.expensetracker.repository.UserRepo;
import com.harsha.expensetracker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Testing Controller
    @GetMapping("/")
    public String home() {
        return "Expense Tracker API is running!";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            return "Email already registered!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            return "Invalid email!";
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return "Invalid password!";
        }

        // Generate JWT token and return
        String token = jwtUtil.generateToken(user.get().getEmail());
        return Map.of(
                "message","Login successful!",
                "token",token
        ).toString();
    }
}
