package com.example.JwtDemo.Services;

import com.example.JwtDemo.Entity.User;

public interface UserService {
    public User saveUser(User user);

    public void deleteAll();

    public User authenticate(User user) throws Exception;
}
