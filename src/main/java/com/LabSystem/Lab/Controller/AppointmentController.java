package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Appointment;
import com.LabSystem.Lab.Repostory.AppointmentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/appointments")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment) {
        // Generate Pay ID (You can implement your logic here)
        String paymentId = generatePaymentId();

        // Set the generated Pay ID
        appointment.setPaymentId(paymentId);

        // Save the appointment with Pay ID
        appointmentRepository.save(appointment);

        // Construct success message with appointment details
        String successMessage = "Appointment created successfully." +
                " \nAppointment Details: " +
                "\nPay ID: " + paymentId +
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

    // Method to generate Pay ID (you can implement your logic here)
    private String generatePaymentId() {
        // Implement your logic to generate Pay ID, for example:
        return "PYMT" + System.currentTimeMillis(); // This is just a sample logic, you can use your own logic here
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
