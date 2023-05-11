package com.springmanju.fullstackback.controller;

import com.springmanju.fullstackback.exception.UserNotFoundException;
import com.springmanju.fullstackback.model.User;
import com.springmanju.fullstackback.repository.UserRepositery;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepositery userRepositery;
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepositery.save(newUser);

    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepositery.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return  userRepositery.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));


    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepositery.findById(id)
                .map(user-> {
                    user.setUsername((newUser.getUsername()));
                    user.setName(newUser.getName());
                    user.setEmail((newUser.getEmail()));
                    return userRepositery.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepositery.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepositery.deleteById(id);
        return "user with id "+id+"has been deleted success";
    }
}
