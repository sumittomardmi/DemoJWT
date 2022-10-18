package com.example.JwtDemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
