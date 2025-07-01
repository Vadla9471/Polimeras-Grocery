package com.Polimeras.Controller;

import com.Polimeras.Entity.Users;
import com.Polimeras.Repository.UsersRepository;
import com.Polimeras.Service.EmailService;
import com.Polimeras.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/profile")
    public ResponseEntity<Optional<Users>> getProfile(Principal principal){
        Optional<Users> users = userService.getUserDetails(principal);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/dashboard")
    public String display(){
        return "This is Customer dash board";
    }


}
