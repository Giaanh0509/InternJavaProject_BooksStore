package com.example.bookstore.service;
import com.example.bookstore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    public User findByUsername(String username);
    public User saveUser(User user);
}
