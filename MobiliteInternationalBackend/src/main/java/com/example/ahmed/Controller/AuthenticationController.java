package com.example.ahmed.Controller;

import com.example.ahmed.Auth.AuthenticationRequest;
import com.example.ahmed.Auth.AuthenticationResponse;

import com.example.ahmed.Entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Auth")//v1
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService service;


@CrossOrigin("http://localhost:4200")
    @PostMapping ("/Authen")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {


        return ResponseEntity.ok(service.authenticate(request));
    }
//    @CrossOrigin("http://localhost:4200")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/admin")
//    public String admin() {
//        return "Hello, admin!";
//    }
//    @CrossOrigin("http://localhost:4200")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/user")
//    public String user() {
//        return "Hello, user!";
//    }
}
