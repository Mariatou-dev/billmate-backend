package com.billmate.mybillmate.services;

import com.billmate.mybillmate.models.User;
import com.billmate.mybillmate.repositories.UserRepository;
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
    //private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Validate password
        validatePassword(user.getPassword());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        System.out.println("Users retrieved from database: " + users);
        return users;
    }

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id "+id+" not found"));
    }

    public Optional<User> findByCredentials(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    public User updateUser(User updatedUser, Long id) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id "+id+" not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastname(updatedUser.getLastName());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setEmail(updatedUser.getEmail());

        return this.userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id "+id+" not found"));
        this.userRepository.delete(existingUser);
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("The password must not be null or empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("The password must be at least 6 characters long");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
