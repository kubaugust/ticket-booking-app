package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.services.interfaces.ICurrentTimeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CurrentTimeService implements ICurrentTimeService {
    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
