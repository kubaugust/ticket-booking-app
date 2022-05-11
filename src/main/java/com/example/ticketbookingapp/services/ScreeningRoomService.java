package com.example.ticketbookingapp.services;

import com.example.ticketbookingapp.validators.BindingValidator;
import com.example.ticketbookingapp.models.ScreeningRoom;
import com.example.ticketbookingapp.repositories.ScreeningRoomRepository;
import com.example.ticketbookingapp.services.interfaces.IFindEntityService;
import com.example.ticketbookingapp.services.interfaces.IScreeningRoomService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class ScreeningRoomService implements IScreeningRoomService {
    private final ScreeningRoomRepository repository;
    private final IFindEntityService findEntityService;

    public ScreeningRoomService(ScreeningRoomRepository repository, IFindEntityService findEntityService) {
        this.repository = repository;
        this.findEntityService = findEntityService;
    }

    @Override
    public List<ScreeningRoom> getAllScreeningRooms() {
        return repository.findAll();
    }

    @Override
    public ScreeningRoom getScreeningRoom(Long id) {
        return findEntityService.findEntityOrThrowException(id, repository, ScreeningRoom.class);
    }

    @Override
    public ScreeningRoom addScreeningRoom(ScreeningRoom newScreeningRoom, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        return repository.save(newScreeningRoom);
    }

    @Override
    public ScreeningRoom updateScreeningRoom(ScreeningRoom updatedScreeningRoom, Long id, BindingResult result) {
        BindingValidator.validateEntityOrThrowException(result);
        ScreeningRoom screeningRoom = findEntityService.findEntityOrThrowException(id, repository, ScreeningRoom.class);
        screeningRoom.setNumber(updatedScreeningRoom.getNumber());
        return repository.save(screeningRoom);
    }

    @Override
    public void deleteScreeningRoom(Long id) {
        repository.delete(findEntityService.findEntityOrThrowException(id, repository, ScreeningRoom.class));
    }

    /*private ScreeningRoom findScreeningRoomOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("There isn't any screening room with id=" + id + " in the database."));
    }*/
}
