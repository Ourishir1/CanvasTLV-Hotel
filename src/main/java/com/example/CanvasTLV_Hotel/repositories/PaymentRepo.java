package com.example.CanvasTLV_Hotel.repositories;

import com.example.CanvasTLV_Hotel.modules.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
