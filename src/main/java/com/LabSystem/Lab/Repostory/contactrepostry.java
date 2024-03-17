package com.LabSystem.Lab.Repostory;
import  com.LabSystem.Lab.Model.contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface contactrepostry extends MongoRepository<contact, Integer>{

}

