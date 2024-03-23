package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Appointment;
import com.LabSystem.Lab.Repostory.AppointmentRepository;
import com.LabSystem.Lab.email.SendEmailEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher; // Autowire ApplicationEventPublisher

    // POST method to create a new appointment
    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);

        sendConfirmationEmail(savedAppointment);

        return savedAppointment;
    }

    private void sendConfirmationEmail(Appointment appointment) {
        String paymentId = String.valueOf(appointment.getPaymentId());
        String toEmail = appointment.getEmail();
        String subject = "Appointment Confirmation";
        String body = "Hi " + appointment.getName() + ",\n\n" +
                "Your appointment has been confirmed.\n" +
                "Payment ID: " + paymentId + "\n" +
                "Your ID: " + appointment.getId() + "\n" +
                "Date: " + appointment.getDate() + "\n" +
                "Time: " + appointment.getTime() + "\n" +
                "Address: " + appointment.getAddress() + "\n" +
                "Phone Number: " + appointment.getPhoneNumber() + "\n\n" +
                "Thank you for choosing our service.\n\n" +
                "Best Regards,\n" +
                "ABCLABCAREPRO System Message ......,\n";

        // Publish SendEmailEvent
        eventPublisher.publishEvent(new SendEmailEvent(this, toEmail, subject, body));
    }

    // GET method to retrieve all appointments
    @GetMapping("/getAllAppointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    // GET method to retrieve appointment by ID
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        // Find appointment by ID in the repository
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        // Check if the appointment exists
        if (appointment.isPresent()) {
            // If found, return it with HttpStatus OK
            return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
        } else {
            // If not found, return HttpStatus NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE method to delete appointment by paymentId
    @DeleteMapping("/appointments/{paymentId}")
    public ResponseEntity<String> deleteAppointmentByPaymentId(@PathVariable String paymentId) {
        // Find appointment by paymentId in the repository
        Optional<Appointment> appointmentOptional = appointmentRepository.findByPaymentId(paymentId);

        // Check if the appointment exists
        if (appointmentOptional.isPresent()) {
            // If found, delete it from the repository
            appointmentRepository.delete(appointmentOptional.get());
            // Return success response
            return new ResponseEntity<>("Appointment with paymentId " + paymentId + " deleted successfully.", HttpStatus.OK);
        } else {
            // If not found, return HttpStatus NOT_FOUND
            return new ResponseEntity<>("Appointment with paymentId " + paymentId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // PUT method to update appointment by paymentId
    @PutMapping("/appointments/{paymentId}")
    public ResponseEntity<String> updateAppointmentByPaymentId(@PathVariable String paymentId, @RequestBody Appointment updatedAppointment) {
        // Find appointment by paymentId in the repository
        Optional<Appointment> appointmentOptional = appointmentRepository.findByPaymentId(paymentId);

        // Check if the appointment exists
        if (appointmentOptional.isPresent()) {
            // Get the existing appointment
            Appointment existingAppointment = appointmentOptional.get();

            // Update the existing appointment with the new data
            existingAppointment.setName(updatedAppointment.getName());
            existingAppointment.setEmail(updatedAppointment.getEmail());
            existingAppointment.setAddress(updatedAppointment.getAddress());
            existingAppointment.setPhoneNumber(updatedAppointment.getPhoneNumber());
            existingAppointment.setDate(updatedAppointment.getDate());
            existingAppointment.setTime(updatedAppointment.getTime());
            existingAppointment.setTest(updatedAppointment.getTest());

            // Save the updated appointment
            appointmentRepository.save(existingAppointment);

            // Return success response with a message
            String successMessage = "Appointment with paymentId " + paymentId + " updated successfully.";
            return new ResponseEntity<>(successMessage, HttpStatus.OK);
        } else {
            // If appointment not found, return HttpStatus NOT_FOUND
            return new ResponseEntity<>("Appointment with paymentId " + paymentId + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
