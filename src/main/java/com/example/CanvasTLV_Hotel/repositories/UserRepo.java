package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByPassword(String password);
    Boolean existsByPhoneNumber(String phoneNumber);


}
