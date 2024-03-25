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
    public void testUpdateAppointmentByPaymentId() {
        // Create a sample paymentId
        String paymentId = "12345";

        // Create a sample appointment object
        Appointment existingAppointment = new Appointment();
        existingAppointment.setPaymentId(paymentId);
        existingAppointment.setName("John Doe");
        existingAppointment.setId(1);
        existingAppointment.setEmail("johndoe@example.com");
        existingAppointment.setAddress("123 Main St");
        existingAppointment.setPhoneNumber("1234567890");
        existingAppointment.setDate("2024-03-24");
        existingAppointment.setTime("10:00 AM");

        // Create a sample updated appointment object
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setPaymentId(paymentId);
        updatedAppointment.setName("Jane Doe");
        updatedAppointment.setId(1);
        updatedAppointment.setEmail("janedoe@example.com");
        updatedAppointment.setAddress("456 Elm St");
        updatedAppointment.setPhoneNumber("9876543210");
        updatedAppointment.setDate("2024-04-24");
        updatedAppointment.setTime("11:00 AM");

        // Mock behavior of appointmentRepository.findByPaymentId() method
        when(appointmentRepository.findByPaymentId(paymentId)).thenReturn(Optional.of(existingAppointment));
        // Mock behavior of appointmentRepository.save() method
        when(appointmentRepository.save(existingAppointment)).thenReturn(existingAppointment);

        // Call the method under test
        ResponseEntity<String> responseEntity = appointmentController.updateAppointmentByPaymentId(paymentId, updatedAppointment);

        // Verify that appointmentRepository.findByPaymentId() was called once
        verify(appointmentRepository, times(1)).findByPaymentId(paymentId);
        // Verify that appointmentRepository.save() was called once
        verify(appointmentRepository, times(1)).save(existingAppointment);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Appointment with paymentId " + paymentId + " updated successfully.", responseEntity.getBody());

        // Verify that the existing appointment was updated correctly
        assertEquals("Jane Doe", existingAppointment.getName());
        assertEquals("janedoe@example.com", existingAppointment.getEmail());
        assertEquals("456 Elm St", existingAppointment.getAddress());
        assertEquals("9876543210", existingAppointment.getPhoneNumber());
        assertEquals("2024-04-24", existingAppointment.getDate());
        assertEquals("11:00 AM", existingAppointment.getTime());
    }
}