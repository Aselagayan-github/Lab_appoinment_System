package com.LabSystem.Lab.Repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.LabSystem.Lab.Model.Pay;

import java.util.Optional;

public interface Payrepository extends MongoRepository<Pay,Integer> {

}
