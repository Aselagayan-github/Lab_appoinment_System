package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Repostory.contactrepostry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.LabSystem.Lab.Repostory.Userrepository;
import com.LabSystem.Lab.Model.User;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")

public class UserControllor {

    @Autowired
    Userrepository userrepository;
    @PostMapping("/adduser")
    public void adduser(@RequestBody User user){

        userrepository.save(user);
    }
}
