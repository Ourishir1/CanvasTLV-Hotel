package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {

}
