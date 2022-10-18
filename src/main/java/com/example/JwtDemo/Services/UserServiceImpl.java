package com.example.JwtDemo.Services;

import com.example.JwtDemo.Entity.User;
import com.example.JwtDemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }

    @Override
    public User authenticate(User user) throws Exception {
       Optional<User> optUser = userRepository.findByEmail(user.getEmail());
       if(optUser.isPresent()){
           if(optUser.get().getPassword().equals(user.getPassword())){
               return optUser.get();
           }else{
               throw new Exception("Password did no match");
           }
       }else{
           throw new Exception("Email did not match");
       }
    }


}
