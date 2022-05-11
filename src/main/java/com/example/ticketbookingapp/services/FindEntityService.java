package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FindEntityService implements IFindEntityService {

    @Override
    public <T> T findEntityOrThrowException(Long id, JpaRepository<T, Long> repository, Class<T> errorClass) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There isn't any " + errorClass.getSimpleName() + " with id=" + id + " in the database."));
    }
}
