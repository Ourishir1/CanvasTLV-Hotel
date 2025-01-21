package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.Reservation;
import com.example.CanvasTLV_Hotel.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
   @Query( "SELECT r FROM Reservation r WHERE r.room.id = :roomId " +
            "AND NOT (r.endDate <= :startDate OR r.startDate >= :endDate)")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
   List<Reservation> findReservationsByUser(User user);
}

