package com.LabSystem.Lab.Paymentservicetest;

import com.LabSystem.Lab.Controller.PayControllor;
import com.LabSystem.Lab.Model.Pay;
import com.LabSystem.Lab.Repostory.Payrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PayControllerpostTest {

    @InjectMocks
    private PayControllor payControllor;

    @Mock
    private Payrepository payRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPayment() {
        // Create a sample payment object
        Pay payment = new Pay();
        payment.setApoinmentidno("12345");
        payment.setName("John Doe");
        payment.setCardno("1234567890123456");
        payment.setCvc("123");
        payment.setDate("2024-03-24");
        payment.setPayamount("100");

        // Mock behavior of payRepository.save() method
        when(payRepository.save(payment)).thenReturn(payment);

        // Call the method under test
        ResponseEntity<String> responseEntity = payControllor.addPayment(payment);

        // Verify that payRepository.save() was called once
        verify(payRepository, times(1)).save(payment);

        // Verify the response
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Payment added successfully - , \nCard Holder Name: John Doe, \nCard Number: 1234567890123456, \nDate: 2024-03-24, \nAmount: 100", responseEntity.getBody());
    }

    @Test
    public void testGetAllPayments() {
        // Create a sample list of payments
        List<Pay> payments = new ArrayList<>();
        Pay payment1 = new Pay();
        payment1.setApoinmentidno("12345");
        payment1.setName("John Doe");
        payment1.setCardno("1234567890123456");
        payment1.setCvc("123");
        payment1.setDate("2024-03-24");
        payment1.setPayamount("100");
        payments.add(payment1);

        // Mock behavior of payRepository.findAll() method
        when(payRepository.findAll()).thenReturn(payments);

        // Call the method under test
        ResponseEntity<List<Pay>> responseEntity = payControllor.getAllPayments();

        // Verify the response
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(payments, responseEntity.getBody());
    }
    @Test
    public void testGetPaymentsByName() {
        // Create a sample list of payments for a specific name
        String testName = "John Doe";
        List<Pay> payments = new ArrayList<>();
        Pay payment1 = new Pay();
        payment1.setApoinmentidno("12345");
        payment1.setName(testName);
        payment1.setCardno("1234567890123456");
        payment1.setCvc("123");
        payment1.setDate("2024-03-24");
        payment1.setPayamount("100");
        payments.add(payment1);

        // Mock behavior of payRepository.findByName() method
        when(payRepository.findByName(testName)).thenReturn(payments);

        // Call the method under test
        ResponseEntity<List<Pay>> responseEntity = payControllor.getPaymentsByName(testName);

        // Verify the response
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(payments, responseEntity.getBody());
    }
    @Test
    public void testUpdatePaymentByCardNo() {
        // Create a sample payment object
        String cardNo = "1234567890123456";
        Pay existingPayment = new Pay();
        existingPayment.setApoinmentidno("12345");
        existingPayment.setName("John Doe");
        existingPayment.setCardno(cardNo);
        existingPayment.setCvc("123");
        existingPayment.setDate("2024-03-24");
        existingPayment.setPayamount("100");

        // Create a sample updated payment object
        Pay updatedPayment = new Pay();
        updatedPayment.setApoinmentidno("12345");
        updatedPayment.setName("Jane Doe");
        updatedPayment.setCardno(cardNo);
        updatedPayment.setCvc("456");
        updatedPayment.setDate("2024-04-24");
        updatedPayment.setPayamount("150");

        // Mock behavior of payRepository.findByCardno() method
        when(payRepository.findByCardno(cardNo)).thenReturn(Optional.of(existingPayment));
        // Mock behavior of payRepository.save() method
        when(payRepository.save(existingPayment)).thenReturn(existingPayment);

        // Call the method under test
        ResponseEntity<String> responseEntity = payControllor.updatePaymentByCardNo(cardNo, updatedPayment);

        // Verify that payRepository.findByCardno() was called once
        verify(payRepository, times(1)).findByCardno(cardNo);
        // Verify that payRepository.save() was called once
        verify(payRepository, times(1)).save(existingPayment);

        // Verify the response
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Payment with card number " + cardNo + " updated successfully.", responseEntity.getBody());
        // Verify that the existing payment was updated correctly
        assertEquals("Jane Doe", existingPayment.getName());
        assertEquals("456", existingPayment.getCvc());
        assertEquals("2024-04-24", existingPayment.getDate());
        assertEquals("150", existingPayment.getPayamount());
    }
    @Test
    public void testDeletePaymentByAppointmentId() {
        // Create a sample appointment ID
        Long appointmentId = 12345L;

        // Create a sample payment object
        Pay existingPayment = new Pay();
        existingPayment.setApoinmentidno(String.valueOf(appointmentId));
        existingPayment.setName("John Doe");
        existingPayment.setCardno("1234567890123456");
        existingPayment.setCvc("123");
        existingPayment.setDate("2024-03-24");
        existingPayment.setPayamount("100");

        // Mock behavior of payRepository.findById() method
        when(payRepository.findById(Math.toIntExact(appointmentId))).thenReturn(Optional.of(existingPayment));
        // Mock behavior of payRepository.deleteById() method
        doNothing().when(payRepository).deleteById(Math.toIntExact(appointmentId));

        // Call the method under test
        ResponseEntity<String> responseEntity = payControllor.deletePaymentByAppointmentId(appointmentId);

        // Verify that payRepository.findById() was called once
        verify(payRepository, times(1)).findById(Math.toIntExact(appointmentId));
        // Verify that payRepository.deleteById() was called once
        verify(payRepository, times(1)).deleteById(Math.toIntExact(appointmentId));

        // Verify the response
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Payment with Appointment ID " + appointmentId + " deleted successfully.", responseEntity.getBody());
    }
}

