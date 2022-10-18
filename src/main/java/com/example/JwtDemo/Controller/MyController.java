package com.example.JwtDemo.Controller;

import com.example.JwtDemo.Entity.Person;
import com.example.JwtDemo.Exceptions.UnAuthorizedException;
import com.example.JwtDemo.Services.PersonService;
import com.example.JwtDemo.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    public static final String PERSON_URL = "/person";

    @Autowired
    private PersonService personService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(PERSON_URL)
    public ResponseEntity<?> create(@RequestBody Person person, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        try {
            Person savedPerson = personService.savePerson(person);
            jwtTokenUtil.validateAccessToken(authorization);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        }
        catch(UnAuthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(PERSON_URL)
    public ResponseEntity<?> getList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        try{
            jwtTokenUtil.validateAccessToken(authorization);
            return ResponseEntity.ok().body(personService.findAll());
        }
        catch(UnAuthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(PERSON_URL)
    public void deleteAll() {
        personService.deleteAll();
    }

}
