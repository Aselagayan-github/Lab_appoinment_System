package com.LabSystem.Lab.Repostory;

import com.LabSystem.Lab.Model.Pay;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface Payrepository extends MongoRepository<Pay,Integer> {
    @Id
    List<Pay> findByName(String name);

    @Id
    Optional<Pay> findByCardno(String cardNo);
}
