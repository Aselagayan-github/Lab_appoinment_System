package com.LabSystem.Lab.Controller;

import com.LabSystem.Lab.Model.Pay;
import com.LabSystem.Lab.Repostory.Payrepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // GET method to fetch payments by name
    @GetMapping("/getPaymentsByName/{name}")
    public ResponseEntity<List<Pay>> getPaymentsByName(@PathVariable("name") String name) {
        List<Pay> payments = payrepository.findByName(name);
        if (payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(payments);
    }

    // PUT method to update payment by card number
    @PutMapping("/updatePayment/{cardno}")
    public ResponseEntity<String> updatePaymentByCardNo(@PathVariable("cardno") String cardNo, @RequestBody Pay updatedPayment) {
        Optional<Pay> existingPaymentOptional = payrepository.findByCardno(cardNo);
        if (existingPaymentOptional.isPresent()) {
            Pay existingPayment = existingPaymentOptional.get();

            // Update the fields of the existing payment with the values from the updated payment
            existingPayment.setApoinmentidno(updatedPayment.getApoinmentidno());
            existingPayment.setName(updatedPayment.getName());
            existingPayment.setCardno(updatedPayment.getCardno());
            existingPayment.setCvc(updatedPayment.getCvc());
            existingPayment.setDate(updatedPayment.getDate());
            existingPayment.setPayamount(updatedPayment.getPayamount());

            // Save the updated payment
            payrepository.save(existingPayment);

            return ResponseEntity.ok().body("Payment with card number " + cardNo + " updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
