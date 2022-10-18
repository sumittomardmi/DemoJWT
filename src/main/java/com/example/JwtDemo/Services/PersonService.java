package com.example.JwtDemo.Services;

import com.example.JwtDemo.Entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PersonService {

    Person savePerson(Person person);

    List<Person> findAll();

    void deleteAll();
}
