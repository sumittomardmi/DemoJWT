package com.example.JwtDemo.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    @NotNull @Email
    private String email;

    @NotNull @Length(min = 5, max = 10)
    private String password;
}
