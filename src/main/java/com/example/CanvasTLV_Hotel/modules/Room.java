package com.example.CanvasTLV_Hotel.modules;

import com.example.CanvasTLV_Hotel.modules.enums.RoomType;
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
    private RoomType type;
    private double pricePerNight;
    private boolean isAvailable=true;
    private String description;
    private int capacity;
    private List<String> features;
    @OneToMany
    private Reservation reservation;

    public Room(String roomNumber, RoomType type, double pricePerNight, boolean isAvailable, String description, int capacity, List<String> features) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.description = description;
        this.capacity = capacity;
        this.features = features;
    }
}
