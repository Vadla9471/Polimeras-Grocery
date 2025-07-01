package com.Polimeras.Service;

import com.Polimeras.Entity.Users;
import com.Polimeras.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    public Optional<Users> getUserDetails(Principal principal) {
        return usersRepository.findByUsername(principal.getName());
    }

    public Users setNewUSer(Users user) {
        user.setRole(user.getRole());
        user.setCreatedAt(LocalDateTime.now());
        Users users = usersRepository.save(user);
        if (users.isActive()){
            emailService.newRegister(users.getFirstname(),users.getId(),users.getEmail());
        }
        return users;
    }

    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
