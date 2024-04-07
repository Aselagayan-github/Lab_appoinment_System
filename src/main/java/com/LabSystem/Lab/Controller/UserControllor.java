package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.contact;
import com.LabSystem.Lab.Repostory.contactrepostry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.LabSystem.Lab.Repostory.Userrepository;
import com.LabSystem.Lab.Model.User;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")

public class UserControllor {

    @Autowired
    Userrepository userrepository;
    @PostMapping("/adduser")
    public void adduser(@RequestBody User user){

        userrepository.save(user);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> user = userrepository.findAll();
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String idno) {
        Optional<User> userOptional = userrepository.findById(Integer.valueOf(idno));
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable String id, @RequestBody User newUser) {
        try {
            Optional<User> userOptional = userrepository.findById(Integer.valueOf(id));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setName(newUser.getName());
                user.setAddress(newUser.getAddress());
                user.setContactno(newUser.getContactno());
                user.setUsertype(newUser.getUsertype());
                user.setQulification(newUser.getQulification());
                user.setExperience(newUser.getExperience());
                user.setPassword(newUser.getPassword());

                userrepository.save(user);
                return ResponseEntity.ok("User with ID " + id + " updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error occurred while updating user: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String idno) {
        try {
            Optional<User> userOptional = userrepository.findByIdno(idno);
            if (userOptional.isPresent()) {
                userrepository.deleteById(Integer.valueOf(idno));
                return ResponseEntity.ok("User with ID " + idno + " deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + idno + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while deleting user: " + e.getMessage());
        }
    }
}