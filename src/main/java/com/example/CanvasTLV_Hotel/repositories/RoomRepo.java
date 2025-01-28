package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.Room;
import com.example.CanvasTLV_Hotel.modules.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {
    List<Room> findRoomsByRoomType(RoomType roomType);
    List<Room> findByIsAvailableTrue();
}

