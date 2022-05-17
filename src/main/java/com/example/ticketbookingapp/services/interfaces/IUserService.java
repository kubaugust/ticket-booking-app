package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.User;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUser(Long id);
    User addUser(User newUser, BindingResult result);
    User updateUser(User updatedUser, Long id, BindingResult result);
    void deleteUser(Long id);
}
