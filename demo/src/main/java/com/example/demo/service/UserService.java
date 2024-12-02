package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setShippingAddress(updatedUser.getShippingAddress());
            existingUser.setBillingAddress(updatedUser.getBillingAddress());
            return userRepository.save(existingUser);
        });
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}