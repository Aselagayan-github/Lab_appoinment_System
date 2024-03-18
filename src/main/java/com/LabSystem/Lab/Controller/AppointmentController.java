package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Appointment;
import com.LabSystem.Lab.Repostory.AppointmentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/appointments")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment) {
        // Generate Payment ID (You can implement your logic here)
        String paymentId = generatePaymentId();

        // Set the generated Payment ID
        appointment.setPaymentId(paymentId);

        // Save the appointment with Payment ID
        appointmentRepository.save(appointment);

        // Construct success message with appointment details
        String successMessage = "Appointment created successfully." +
                " \nAppointment Details: " +
                "\nPayment ID: " + paymentId +
                "\nName: " + appointment.getName() +
                "\nID: " + appointment.getId() +
                "\nEmail: " + appointment.getEmail() +
                "\nAddress: " + appointment.getAddress() +
                "\nPhone Number: " + appointment.getPhoneNumber() +
                "\nDate: " + appointment.getDate() +
                "\nTime: " + appointment.getTime() +
                "\nTesting Type: " + appointment.getTest();

        // Return success response
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    // Method to generate Payment ID (you can implement your logic here)
    private String generatePaymentId() {
        // Implement your logic to generate Payment ID, for example:
        return "PYMT" + System.currentTimeMillis(); // This is just a sample logic, you can use your own logic here
    }
    // GET method to retrieve all appointments
    @GetMapping("/getAllAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }


}
