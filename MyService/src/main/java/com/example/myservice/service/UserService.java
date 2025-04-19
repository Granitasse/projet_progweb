package com.example.myservice.service;

import com.example.myservice.entity.User;
import com.example.myservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAdmin(false);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String[] authorities;
        if (user.isAdmin()) authorities = new String[]{"ADMIN"};
        else authorities = new String[0];

        return org.springframework.security.core.userdetails.User.builder()
                                                                 .username(user.getUsername())
                                                                 .password(user.getPassword())
                                                                 .authorities(authorities)
                                                                 .build();
    }
}