package com.LabSystem.Lab.Repostory;
import  com.LabSystem.Lab.Model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface Resultrepository extends MongoRepository<Result,Integer>{
}
