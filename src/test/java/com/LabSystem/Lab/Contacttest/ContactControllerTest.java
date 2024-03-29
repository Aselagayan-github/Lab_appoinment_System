package com.LabSystem.Lab.Contacttest;

import com.LabSystem.Lab.Controller.contactcontroller;
import com.LabSystem.Lab.Model.contact;
import com.LabSystem.Lab.Repostory.contactrepostry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContactControllerTest {

    @InjectMocks
    private contactcontroller contactController;

    @Mock
    private contactrepostry contactRepostry;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContact() {
        // Create a sample contact object
        contact contact = new contact();
        contact.setName("John Doe");
        contact.setId(1);
        contact.setEmail("johndoe@example.com");
        contact.setSubject("Test Subject");
        contact.setMessage("Test Message");

        // Call the method under test
        contactController.addContact(contact);

        // Verify that contactRepostry.save() was called once with the correct contact object
        verify(contactRepostry, times(1)).save(contact);
    }
    @Test
    public void testGetAllContacts() {
        // Create a sample list of contacts
        List<contact> contacts = new ArrayList<>();
        contact contact1 = new contact();
        contact1.setName("John Doe");
        contact1.setId(1);
        contact1.setEmail("johndoe@example.com");
        contact1.setSubject("Subject 1");
        contact1.setMessage("Message 1");
        contacts.add(contact1);

        // Mock behavior of contactRepostry.findAll() method
        when(contactRepostry.findAll()).thenReturn(contacts);

        // Call the method under test
        ResponseEntity<List<contact>> responseEntity = contactController.getAllContacts();

        // Verify the response status code
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify the response body
        assertEquals(contacts, responseEntity.getBody());
    }
    @Test
    public void testDeleteContact_NotFound() {
        // Create a sample contact ID
        int id = 1;

        // Mock behavior of contactRepostry.deleteById() method to throw an exception
        doThrow(new RuntimeException()).when(contactRepostry).deleteById(id);

        // Call the method under test
        ResponseEntity<?> responseEntity = contactController.deleteContact(id);

        // Verify that contactRepostry.deleteById() was called once with the correct ID
        verify(contactRepostry, times(1)).deleteById(id);

        // Verify the response status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    @Test
    public void testUpdateContact_NotFound() {
        // Create a sample contact ID
        int id = 1;

        // Create a sample updated contact object
        contact updatedContact = new contact("Jane Doe", id, "janedoe@example.com", "Subject 2", "Message 2");

        // Mock behavior of contactRepostry.findById() method to return empty optional
        when(contactRepostry.findById(id)).thenReturn(Optional.empty());

        // Call the method under test
        ResponseEntity<?> responseEntity = contactController.updateContact(id, updatedContact);

        // Verify that contactRepostry.findById() was called once with the correct ID
        verify(contactRepostry, times(1)).findById(id);

        // Verify the response status code
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateContact_InternalServerError() {
        // Create a sample contact ID
        int id = 1;

        // Create a sample existing contact object
        contact existingContact = new contact("John Doe", id, "johndoe@example.com", "Subject 1", "Message 1");

        // Create a sample updated contact object
        contact updatedContact = new contact("Jane Doe", id, "janedoe@example.com", "Subject 2", "Message 2");

        // Mock behavior of contactRepostry.findById() method
        when(contactRepostry.findById(id)).thenReturn(Optional.of(existingContact));
        // Mock behavior of contactRepostry.save() method to throw an exception
        when(contactRepostry.save(existingContact)).thenThrow(new RuntimeException());

        // Call the method under test
        ResponseEntity<?> responseEntity = contactController.updateContact(id, updatedContact);

        // Verify that contactRepostry.findById() was called once with the correct ID
        verify(contactRepostry, times(1)).findById(id);
        // Verify that contactRepostry.save() was called once with the existing contact object
        verify(contactRepostry, times(1)).save(existingContact);

        // Verify the response status code
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
