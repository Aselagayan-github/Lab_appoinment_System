package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Payment;
import com.LabSystem.Lab.Repostory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    // POST method to add payment
    @PostMapping("/addpayment")
    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
        paymentRepository.save(payment);
        String message = "Payment added successfully - " +

                ", Card Holder Name: " + payment.getName() +
                ", Card Number: " + payment.getCardno() +
                ", Date: " + payment.getDate() +
                ", Amount: " + payment.getPayamount();
        return ResponseEntity.ok().body(message);
    }

    // GET method to retrieve all payments
    @GetMapping("/getAllPayments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok().body(payments);
    }

    // DELETE method to delete payment by appointment ID
    @DeleteMapping("/deletePayment/{appointmentId}")
    public ResponseEntity<Void> deletePaymentByAppointmentId(@PathVariable String appointmentId) {
        paymentRepository.deleteById(appointmentId);
        return ResponseEntity.noContent().build();
    }
}
