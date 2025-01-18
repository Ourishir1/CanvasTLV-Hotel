package com.example.CanvasTLV_Hotel.modules;

import com.example.CanvasTLV_Hotel.modules.enums.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String roomNumber;
    private Type type;
    private double pricePerNight;
    private boolean isAvailable;
    private String description;
    private int capacity;
    private List<String> features;
    @OneToMany
    private Reservation reservation;
}
