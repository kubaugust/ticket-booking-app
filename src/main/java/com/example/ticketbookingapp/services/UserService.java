package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.models.User;
import com.example.ticketbookingapp.repositories.UserRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;
    private final IFindEntityService findEntityService;

    public UserService(UserRepository repository, IFindEntityService findEntityService) {
        this.repository = repository;
        this.findEntityService = findEntityService;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return findEntityService.findEntityOrThrowException(id, repository, User.class);
    }

    @Override
    public User addUser(User newUser, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        return repository.save(newUser);
    }

    @Override
    public User updateUser(User updatedUser, Long id, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        User user = findEntityService.findEntityOrThrowException(id, repository, User.class);
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(findEntityService.findEntityOrThrowException(id, repository, User.class));
    }

    /*private User findUserOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There isn't any user with id=" + id + " in the database."));
    }*/
}
