package com.example.CanvasTLV_Hotel.services;

import com.example.CanvasTLV_Hotel.modules.Room;
import com.example.CanvasTLV_Hotel.modules.User;
import com.example.CanvasTLV_Hotel.repositories.RoomRepo;
import com.example.CanvasTLV_Hotel.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    protected RoomRepo roomRepo;
    protected UserRepo userRepo;

    public AdminService(RoomRepo roomRepo, UserRepo userRepo) {
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
    }
    public void addRoom(Room room) {
        if (room == null || roomRepo.existsById(room.getId())) {
            throw new IllegalArgumentException("Room already exists or invalid data.");
        }
        roomRepo.save(room);
    }
    public void updateRoomDetails(Room room){
        roomRepo.save(room);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }



}
