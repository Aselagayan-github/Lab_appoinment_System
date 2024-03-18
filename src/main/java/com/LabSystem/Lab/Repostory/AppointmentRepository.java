package com.LabSystem.Lab.Repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import  com.LabSystem.Lab.Model.Appointment;

import java.util.Optional;


public interface AppointmentRepository extends MongoRepository<Appointment, Integer>{

    Optional<Appointment> findById(String id);

    Optional<Appointment> findByPaymentId(String paymentId);
}
