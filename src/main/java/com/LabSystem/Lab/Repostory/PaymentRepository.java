package com.LabSystem.Lab.Repostory;

import com.LabSystem.Lab.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface PaymentRepository extends MongoRepository<Payment, Integer>{

    void deleteById(String appointmentId);
}
