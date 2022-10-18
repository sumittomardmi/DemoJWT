package com.example.JwtDemo.Services;

import com.example.JwtDemo.Entity.Person;
import com.example.JwtDemo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }
}
