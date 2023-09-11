package com.example.ahmed.Controller;

import com.example.ahmed.Auth.AuthenticationRequest;
import com.example.ahmed.Auth.AuthenticationResponse;
import com.example.ahmed.Config.JwtUtils;

import com.example.ahmed.Repository.UserRep;
import lombok.RequiredArgsConstructor;
import lombok.var;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRep Rep;


    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
//    public AuthenticationResponse register(RegisterRequest request) {
//   var user = User.builder()
//           .firstname(request.getFirstname())
//           .lastname(request.getLastname())
//           .email(request.getEmail())
//           .password(passwordEncoder.encode(request.getPassword()))
//           .role(Role.USER)
//           .build();
//   Rep.save(user);
//
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                            request.getEmail(),
                                                  request.getPassword()
                                                                         )
                                                                            );
        var user = Rep.findByEmail(request.getEmail())
                                .orElseThrow(NullPointerException::new);


        var jwtToken = jwtUtils.generateToken(user);

        return AuthenticationResponse.builder()
                                        .token(jwtToken)
                                             .build();
    }
}
