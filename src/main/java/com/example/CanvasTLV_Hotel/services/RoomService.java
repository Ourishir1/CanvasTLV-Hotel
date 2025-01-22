package com.example.CanvasTLV_Hotel.services;

import com.example.CanvasTLV_Hotel.modules.Room;
import com.example.CanvasTLV_Hotel.repositories.RoomRepo;
import com.example.CanvasTLV_Hotel.services.Exceptions.RoomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    protected RoomRepo roomRepo;

    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }
    public Room getRoomById(long id) throws RoomNotFoundException {
        return roomRepo.findById(id).orElseThrow(()->new RoomNotFoundException("No such room in our hotel"));

    }
    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }
    public List<Room>  getAvailableRooms(){
        return roomRepo.findByIsAvailableTrue();
    }
    public void updateRoomAvailability(Room room) throws RoomNotFoundException {
        if (!roomRepo.existsById(room.getId())) {
            throw new RoomNotFoundException("Room not found: " + room.getId());
        }
        roomRepo.save(room);
    }

}
