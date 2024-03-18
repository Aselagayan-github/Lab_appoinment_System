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

    @GetMapping("/getAllcontact")
    public ResponseEntity<List<contact>> getAllcontact(){
        List<contact> contact = contactrepostry.findAll();

        if(contact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id") String id) {
        try {
            // Convert the ID to the appropriate type if needed (e.g., Integer)
            // Example: Integer contactId = Integer.parseInt(id);

            // Check if the contact exists
            if (contactrepostry.existsById(Integer.valueOf(id))) {
                // Delete the contact
                contactrepostry.deleteById(Integer.valueOf(id));
                return ResponseEntity.ok().body("Contact with ID " + id + " deleted successfully.");
            } else {
                return ResponseEntity.notFound().build(); // Contact not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete contact with ID " + id + ". " + e.getMessage());
        }
    }


    @PutMapping("/updateContact/{id}")
    public ResponseEntity<?> updateContact(@PathVariable("id") int id, @RequestBody contact updatedContact) {
        try {
            // Check if the contact exists
            if (contactrepostry.existsById(id)) {
                // Update the contact
                updatedContact.setId(id); // Set the ID of the updated contact
                contactrepostry.save(updatedContact);
                return ResponseEntity.ok().body("Contact with ID " + id + " updated successfully.");
            } else {
                return ResponseEntity.notFound().build(); // Contact not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update contact with ID " + id + ". " + e.getMessage());
        }
    }





}
