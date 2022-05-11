package com.example.ticketbookingapp.repositories;

import com.example.ticketbookingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
