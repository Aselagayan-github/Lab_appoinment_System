package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.contact;
import com.LabSystem.Lab.Repostory.contactrepostry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PutMapping("/updateContact/{id}")
    public ResponseEntity<?> updateContact(@PathVariable("id") int id, @RequestBody contact updatedContact) {
        try {
            Optional<contact> contactOptional = contactrepostry.findById(id);
            if (contactOptional.isPresent()) {
                contact existingContact = contactOptional.get();
                existingContact.setName(updatedContact.getName());
                existingContact.setEmail(updatedContact.getEmail());
                existingContact.setSubject(updatedContact.getSubject());
                existingContact.setMessage(updatedContact.getMessage());

                // Save the updated contact
                contactrepostry.save(existingContact);

                return new ResponseEntity<>(existingContact, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Contact not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
