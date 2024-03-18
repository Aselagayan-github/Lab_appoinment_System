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


}
