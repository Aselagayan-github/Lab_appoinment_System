package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Result;
import com.LabSystem.Lab.Repostory.Resultrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/results") // Add a base path for the controller
public class ResultControllor {

    @Autowired
    private Resultrepository resultrepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveResult(@RequestBody Result result) {
        // Assuming your ResultService has a method to save the result
        Result savedResult = resultrepository.save(result);
        if (savedResult != null) {
            String message = "Result saved successfully for Test ID: " + savedResult.getId() +
                    ", \nTest Name: " + savedResult.getName() +
                    ", \nResult: " + savedResult.getResult() +
                    ", \nAppointment ID: " + savedResult.getAppoinmentid() +
                    ", \nGender: " + savedResult.getGender() +
                    ", \nContact No: " + savedResult.getContactno() +
                    ", \nTest Type: " + savedResult.getTesttype();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save result", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultrepository.findAll();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
