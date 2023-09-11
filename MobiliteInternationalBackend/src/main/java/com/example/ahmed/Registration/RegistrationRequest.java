package com.example.ahmed.Registration;

import com.example.ahmed.Entity.Role;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Role   role;




}
