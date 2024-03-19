package com.LabSystem.Lab.Repostory;
import com.LabSystem.Lab.Model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface Testrepository extends MongoRepository<Test,Integer>{
}
