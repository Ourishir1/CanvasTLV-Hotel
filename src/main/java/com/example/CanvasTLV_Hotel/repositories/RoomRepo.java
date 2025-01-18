package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {
}
