package com.example.CanvasTLV_Hotel.modules;

import com.example.CanvasTLV_Hotel.modules.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private OrderStatus status=OrderStatus.PENDING;
    @ManyToOne
    private User user;
    @ManyToOne
    private Room room;

    public Reservation(LocalDate startDate, LocalDate endDate, double totalPrice, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.user = user;
    }
}
