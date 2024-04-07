package com.LabSystem.Lab.Repostory;

import com.LabSystem.Lab.Model.User;
import com.LabSystem.Lab.Model.contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface contactrepostry extends MongoRepository<contact, Integer>{

    static void save(User user) {
    }

}

