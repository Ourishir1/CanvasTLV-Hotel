package com.example.CanvasTLV_Hotel.modules;

import com.example.CanvasTLV_Hotel.modules.enums.Status;
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
    private Status status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Room room;
}
