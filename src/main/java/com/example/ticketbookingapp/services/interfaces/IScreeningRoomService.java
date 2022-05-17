package com.example.ticketbookingapp.services.interfaces;

import com.example.ticketbookingapp.models.ScreeningRoom;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IScreeningRoomService {
    List<ScreeningRoom> getAllScreeningRooms();
    ScreeningRoom getScreeningRoom(Long id);
    ScreeningRoom addScreeningRoom(ScreeningRoom newScreeningRoom, BindingResult result);
    ScreeningRoom updateScreeningRoom(ScreeningRoom updatedScreeningRoom, Long id, BindingResult result);
    void deleteScreeningRoom(Long id);
}
