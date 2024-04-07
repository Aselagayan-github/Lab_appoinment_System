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
public class ResultControllor {

    @Autowired
    private Resultrepository resultRepository;

    @PostMapping("/results")
    public Result createResult(@RequestBody Result result) {
        // Save the result to the database
        return resultRepository.save(result);
    }

    @GetMapping("/getAllResults")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultRepository.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(results);
    }
    @DeleteMapping("/results/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable String id) {
        try {
            // Find the result by ID
            Result result = resultRepository.findById(Integer.valueOf(id)).orElse(null);
            if (result != null) {
                // Delete the result from the database
                resultRepository.delete(result);
                return ResponseEntity.ok().build();
            } else {
                // Return 404 Not Found if result with given ID does not exist
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Return 500 Internal Server Error if an exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}



