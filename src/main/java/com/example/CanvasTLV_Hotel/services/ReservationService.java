package com.example.CanvasTLV_Hotel.services;


import com.example.CanvasTLV_Hotel.modules.Reservation;
import com.example.CanvasTLV_Hotel.modules.Room;
import com.example.CanvasTLV_Hotel.modules.User;
import com.example.CanvasTLV_Hotel.modules.enums.RoomType;
import com.example.CanvasTLV_Hotel.repositories.PaymentRepo;
import com.example.CanvasTLV_Hotel.repositories.ReservationRepo;
import com.example.CanvasTLV_Hotel.repositories.RoomRepo;
import com.example.CanvasTLV_Hotel.repositories.UserRepo;
import com.example.CanvasTLV_Hotel.services.Exceptions.NoAvailableRoomsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    protected ReservationRepo reservationRepo;
    protected PaymentRepo paymentRepo;
    protected RoomRepo roomRepo;

    public ReservationService(ReservationRepo reservationRepo, UserRepo userRepo, PaymentRepo paymentRepo, RoomRepo roomRepo) {
        this.reservationRepo = reservationRepo;
        this.paymentRepo = paymentRepo;
        this.roomRepo = roomRepo;
    }

    public Room assignAvailableRoom(RoomType roomType, LocalDate requestedStartDate, LocalDate requestedEndDate) throws NoAvailableRoomsException {

        // Fetch all rooms of the requested type
        List<Room> rooms = roomRepo.findRoomsByRoomType(roomType);
        // Iterate through the rooms to find an available one
        for (Room room : rooms) {
            // Check if the room is available
            List<Reservation> overlappingReservations = reservationRepo.findOverlappingReservations(
                    room.getId(), requestedStartDate, requestedEndDate);

            if (overlappingReservations.isEmpty()) {
                // Room is available, return it
                return room;
            }
        }

        // No available room found, throw an exception or return null
        throw new NoAvailableRoomsException("No available rooms of type " + roomType + " for the requested dates.");
    }



    public void createReservation(Reservation reservation, RoomType roomType) throws NoAvailableRoomsException {
        if (reservation.getStartDate().isAfter(reservation.getEndDate()) || reservation.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid reservation dates.");
        }
        Room openRoom = assignAvailableRoom(roomType, reservation.getStartDate(), reservation.getEndDate());
        reservation.setRoom(openRoom);
        reservationRepo.save(reservation);


    }

    public List<Reservation> getAllReservations(){
        return reservationRepo.findAll();
    }
    public List<Reservation> getReservationsByUser(User user){
        return reservationRepo.findReservationsByUser(user);
    }
    public void updateReservationOrderStatus(Reservation reservation){
        reservationRepo.save(reservation);
    }

}

