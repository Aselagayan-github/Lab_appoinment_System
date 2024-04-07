package com.LabSystem.Lab.Repostory;

import com.LabSystem.Lab.Model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Userrepository extends MongoRepository <User,Integer>{

    @Id
    Optional<User> findByIdno(String idno);

}
