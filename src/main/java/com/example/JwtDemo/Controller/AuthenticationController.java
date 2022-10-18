package com.example.JwtDemo.Controller;

import com.example.JwtDemo.API.AuthenticationRequest;
import com.example.JwtDemo.API.AuthenticationResponse;
import com.example.JwtDemo.Entity.User;
import com.example.JwtDemo.Services.UserService;
import com.example.JwtDemo.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            User user = userService.authenticate(new User(request.getEmail(), request.getPassword()));
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthenticationResponse response = new AuthenticationResponse(user.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody AuthenticationRequest request) {
        try {
            User user = new User(request.getEmail(), request.getPassword());
            userService.saveUser(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/user")
    public void deleteAll() {
        userService.deleteAll();
    }
}
