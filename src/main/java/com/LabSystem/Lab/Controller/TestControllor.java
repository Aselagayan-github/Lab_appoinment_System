package com.LabSystem.Lab.Controller;
import com.LabSystem.Lab.Model.Test;
import com.LabSystem.Lab.Model.contact;
import com.LabSystem.Lab.Repostory.Testrepository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RestController
@CrossOrigin(origins = "http://localhost:63342")

public class TestControllor {
    @Autowired
    Testrepository testrepository;
    @PostMapping("/addtest")
    public void addtest(@RequestBody Test test){

        testrepository.save(test);
    }
    @GetMapping("/alltest")
    public ResponseEntity<List<Test>> getAlltest() {
        List<Test> test = testrepository.findAll();
        if (test.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
    @DeleteMapping("/deletetest/{id}")
    public ResponseEntity<?> deleteTest(@PathVariable("id") int id) {
        Optional<Test> testOptional = testrepository.findById(id);
        if (testOptional.isPresent()) {
            testrepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updatetest/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") int id, @RequestBody Test updatedTest) {
        Optional<Test> testOptional = testrepository.findById(id);
        if (testOptional.isPresent()) {
            Test existingTest = testOptional.get();

            // Update fields from the updatedTest object
            existingTest.setName(updatedTest.getName()); // Assuming 'name' is a field in your Test class
            existingTest.setId(updatedTest.getId()); // Assuming 'date' is a field in your Test class
            existingTest.setTechnician(updatedTest.getTechnician()); // Assuming 'result' is a field in your Test class
            existingTest.setPrice(updatedTest.getPrice()); // Assuming 'description' is a field in your Test class

            // Similarly, update other fields as needed

            Test savedTest = testrepository.save(existingTest);
            return new ResponseEntity<>(savedTest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}

