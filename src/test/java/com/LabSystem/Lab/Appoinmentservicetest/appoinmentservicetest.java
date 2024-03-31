package com.LabSystem.Lab.Appoinmentservicetest;

import com.LabSystem.Lab.Controller.AppointmentController;
import com.LabSystem.Lab.Model.Appointment;
import com.LabSystem.Lab.Repostory.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class appoinmentservicetest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAppointment() {
        // Create a sample appointment object
        Appointment appointment = new Appointment();
        appointment.setPaymentId("12345");
        appointment.setName("John Doe");
        appointment.setId(1);
        appointment.setEmail("johndoe@example.com");
        appointment.setAddress("123 Main St");
        appointment.setPhoneNumber("1234567890");
        appointment.setDate("2024-03-24");
        appointment.setTime("10:00 AM");

        // Mock behavior of appointmentRepository.save() method
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        // Call the method under test
        Appointment createdAppointment = appointmentController.createAppointment(appointment);

        // Verify that appointmentRepository.save() was called once
        verify(appointmentRepository, times(1)).save(appointment);

        // Verify the response
        assertEquals("12345", createdAppointment.getPaymentId());
        assertEquals("John Doe", createdAppointment.getName());
        assertEquals(1, createdAppointment.getId());
        assertEquals("johndoe@example.com", createdAppointment.getEmail());
        assertEquals("123 Main St", createdAppointment.getAddress());
        assertEquals("1234567890", createdAppointment.getPhoneNumber());
        assertEquals("2024-03-24", createdAppointment.getDate());
        assertEquals("10:00 AM", createdAppointment.getTime());
    }

    @Test
    public void testGetAllAppointments() {
        // Create a sample list of appointments
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment1 = new Appointment();
        appointment1.setPaymentId("12345");
        appointment1.setName("John Doe");
        appointment1.setId(1);
        appointment1.setEmail("johndoe@example.com");
        appointment1.setAddress("123 Main St");
        appointment1.setPhoneNumber("1234567890");
        appointment1.setDate("2024-03-24");
        appointment1.setTime("10:00 AM");
        appointments.add(appointment1);

        // Mock behavior of appointmentRepository.findAll() method
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Call the method under test
        ResponseEntity<List<Appointment>> responseEntity = appointmentController.getAllAppointments();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appointments, responseEntity.getBody());
    }

    @Test
    public void testDeleteAppointmentByPaymentId() {
        // Create a sample paymentId
        String paymentId = "12345";

        // Create a sample appointment object
        Appointment appointment = new Appointment();
        appointment.setPaymentId(paymentId);
        appointment.setName("John Doe");
        appointment.setId(1);
        appointment.setEmail("johndoe@example.com");
        appointment.setAddress("123 Main St");
        appointment.setPhoneNumber("1234567890");
        appointment.setDate("2024-03-24");
        appointment.setTime("10:00 AM");

        // Mock behavior of appointmentRepository.findByPaymentId() method
        when(appointmentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(appointment));
        // Mock behavior of appointmentRepository.delete() method
        doNothing().when(appointmentRepository).delete(appointment);

        // Call the method under test
        ResponseEntity<String> responseEntity = appointmentController.deleteAppointmentByPaymentId(paymentId);

        // Verify that appointmentRepository.findByPaymentId() was called once
        verify(appointmentRepository, times(1)).findByPaymentId(paymentId);
        // Verify that appointmentRepository.delete() was called once
        verify(appointmentRepository, times(1)).delete(appointment);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Appointment with paymentId " + paymentId + " deleted successfully.", responseEntity.getBody());
    }



    @Test
    void testUpdateAppointmentById() {
        // Mock data
        String id = "1";
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(Integer.parseInt(id));
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(Integer.parseInt(id));
        updatedAppointment.setName("Updated Name");

        // Mock repository behavior
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(existingAppointment)).thenReturn(updatedAppointment);

        // Call the controller method
        ResponseEntity<Appointment> response = appointmentController.updateAppointmentById(id, updatedAppointment);

        // Verify repository interactions
        verify(appointmentRepository, times(1)).findById(id);
        verify(appointmentRepository, times(1)).save(existingAppointment);

        // Check the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAppointment, response.getBody());
    }

    @Test
    void testUpdateAppointmentByIdNotFound() {
        // Mock data
        String id = "1";
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId(Integer.parseInt(id));
        updatedAppointment.setName("Updated Name");

        // Mock repository behavior
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<Appointment> response = appointmentController.updateAppointmentById(id, updatedAppointment);

        // Verify repository interactions
        verify(appointmentRepository, times(1)).findById(id);
        verify(appointmentRepository, never()).save(any(Appointment.class));

        // Check the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

}
