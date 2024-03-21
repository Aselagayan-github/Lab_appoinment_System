package com.LabSystem.Lab.Controller;
import com.LabSystem.Lab.Model.contact;
import com.LabSystem.Lab.Repostory.contactrepostry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class contactcontroller {

    @Autowired
    contactrepostry contactrepostry;
    @PostMapping("/addContact")
    public void addContact(@RequestBody contact coontact){

        contactrepostry.save(coontact);
    }
    @GetMapping("/allContacts")
    public ResponseEntity<List<contact>> getAllContacts() {
        List<contact> contacts = contactrepostry.findAll();
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id") int id) {
        try {
            contactrepostry.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
