package com.example.CanvasTLV_Hotel.services;

import com.example.CanvasTLV_Hotel.modules.User;
import com.example.CanvasTLV_Hotel.repositories.UserRepo;
import com.example.CanvasTLV_Hotel.services.Exceptions.EmailAlreadyExistException;
import com.example.CanvasTLV_Hotel.services.Exceptions.PasswordIsIncorrectException;
import com.example.CanvasTLV_Hotel.services.Exceptions.PhoneNumberAlreadyExists;
import com.example.CanvasTLV_Hotel.services.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service("userService")
public class
UserService {
        protected PasswordEncoder passwordEncoder;
        protected UserRepo userRepo;


    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public User login(String email, String rawPassword) throws PasswordIsIncorrectException, UserNotFoundException {
    // Find the user by email
    if (userRepo.existsByEmail(email)) {
        User user = userRepo.findByEmail(email);
        // Check if the raw password matches the encoded password
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        } else
            throw new PasswordIsIncorrectException("Wrong Password");
    } else
        throw new UserNotFoundException("User was not found");
}
    private void validateNewUser(User user) throws EmailAlreadyExistException, PhoneNumberAlreadyExists {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExists("Phone number already in use");
        }
    }
    //User CRUD
    @Transactional
    public void createUser(User user) throws EmailAlreadyExistException, PhoneNumberAlreadyExists {
        validateNewUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Transactional
    public User updateUser(User user) throws UserNotFoundException, EmailAlreadyExistException {
        // Fetch existing user from the database
        User existingUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Check if the new email already exists for another user
        if (userRepo.existsByEmail(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExistException("Email already exists");
        }

        // Update user's personal details
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAdmin(user.isAdmin());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        String newPassword = user.getPassword();
        if (newPassword != null && !newPassword.isBlank() &&
                !passwordEncoder.matches(newPassword, existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }


        // Save the updated user to the database
        return userRepo.save(existingUser);
    }
    @Transactional
    public void deleteUser(long userId) throws UserNotFoundException {
        // Check if the user exists
        if (!userRepo.existsById(userId)){
            throw new UserNotFoundException("User not found");
        }
        userRepo.deleteById(userId);
    }
    public User getUserById(long id) throws UserNotFoundException {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Couldn't find user"));
    }



}
