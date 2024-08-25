package com.example.bookstore.service;

import com.example.bookstore.dao.RolesRepository;
import com.example.bookstore.dao.UsersRepository;
import com.example.bookstore.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.bookstore.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UsersServiceImp implements UsersService {
    private UsersRepository usersRepository;

    private RolesRepository rolesRepository;

    @Autowired
    public UsersServiceImp(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = usersRepository.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }
}
