package com.example.CanvasTLV_Hotel.services;

import com.example.CanvasTLV_Hotel.modules.Reservation;
import com.example.CanvasTLV_Hotel.repositories.PaymentRepo;
import com.example.CanvasTLV_Hotel.repositories.RoomRepo;
import com.example.CanvasTLV_Hotel.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    protected ReservationService reservationService;
    protected UserRepo userRepo;
    protected PaymentRepo paymentRepo;
    protected RoomRepo roomRepo;

    public ReservationService(ReservationService reservationService, UserRepo userRepo, PaymentRepo paymentRepo, RoomRepo roomRepo) {
        this.reservationService = reservationService;
        this.userRepo = userRepo;
        this.paymentRepo = paymentRepo;
        this.roomRepo = roomRepo;
    }


    public void createReservation(Reservation reservation){

    }
//    updateReservationStatus()
//    getReservationsByUser()
//    getAvailableRooms()
//    cancelReservation()
}
