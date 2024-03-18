package com.LabSystem.Lab.Repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import  com.LabSystem.Lab.Model.Appointment;


public interface AppointmentRepository extends MongoRepository<Appointment, Integer>{

}
