package com.theater.service;

import com.theater.entity.Role;
import com.theater.entity.User;
import com.theater.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
    public User addUser(User user){
        if(userRepository.findByEmail(user.getEmail())!=null) return null;
        user.setRole(Role.VIEWER);
        return save(user);
    }
    public User editUser(User fromContext, User user){
        if(!user.getEmail().equals(fromContext.getEmail()))
            if(userRepository.findByEmail(user.getEmail())!=null) return null;
        user.setRole(fromContext.getRole());
        user.setId(fromContext.getId());
        return save(user);
    }
    public User save(User user){
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }
    public User get(int id){ return userRepository.findById(id).get(); }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user==null) return false;
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public void deleteById(Integer id) { userRepository.deleteById(id); }
}
