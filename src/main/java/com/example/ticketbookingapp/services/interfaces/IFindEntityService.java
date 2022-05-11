package com.example.ticketbookingapp.services.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFindEntityService {
    <T> T findEntityOrThrowException(Long id, JpaRepository<T, Long> repository, Class<T> errorClass);
}
