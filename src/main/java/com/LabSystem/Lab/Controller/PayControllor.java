package com.LabSystem.Lab.Controller;
import com.LabSystem.Lab.Model.Pay;
import com.LabSystem.Lab.Repostory.Payrepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class PayControllor {

    @Autowired
    private Payrepository payrepository;

    // POST method to add payment
    @PostMapping("/addpayment")
    public ResponseEntity<String> addPayment(@RequestBody Pay payment) {
        payrepository.save(payment);
        String message = "Payment added successfully - " +

                ", \nCard Holder Name: " + payment.getName() +
                ", \nCard Number: " + payment.getCardno()+
                ", \nDate: " + payment.getDate() +
                ", \nAmount: " + payment.getPayamount();
        return ResponseEntity.ok().body(message);
    }
    // GET method to fetch all payments
    @GetMapping("/getAllPayments")
    public ResponseEntity<List<Pay>> getAllPayments() {
        List<Pay> pay = payrepository.findAll();
        if (pay.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(pay);
    }
    // DELETE method to delete payment by Appointment ID number
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentByAppointmentId(@PathVariable("id") Long appointmentId) {
        Optional<Pay> paymentOptional = payrepository.findById(Math.toIntExact(appointmentId));
        if (paymentOptional.isPresent()) {
            payrepository.deleteById(Math.toIntExact(appointmentId));
            return ResponseEntity.ok().body("Payment with Appointment ID " + appointmentId + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
