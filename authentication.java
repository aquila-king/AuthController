package com.aquila.auth.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Map<String, String> users = new HashMap<>();

    @GetMapping("/health")
    public String health() {
        return "Auth Service Running";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        users.put(username, password);
        return "User Registered";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        if (users.containsKey(username) &&
            users.get(username).equals(password)) {
            return "Login Successful";
        }

        return "Invalid Credentials";
    }
}
