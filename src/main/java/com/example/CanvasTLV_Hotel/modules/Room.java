package com.example.CanvasTLV_Hotel.modules;

import com.example.CanvasTLV_Hotel.modules.enums.RoomType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Rooms")
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String roomNumber;
    private RoomType roomType;
    private double pricePerNight;
    private boolean isAvailable=true;
    private String description;
    private int capacity;
    private List<String> features;
    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private List<Reservation> reservation;

    public Room(String roomNumber, RoomType roomType, double pricePerNight, boolean isAvailable, String description, int capacity, List<String> features) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.description = description;
        this.capacity = capacity;
        this.features = features;
    }

}
