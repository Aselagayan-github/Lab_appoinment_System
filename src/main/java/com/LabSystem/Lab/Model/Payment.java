package com.LabSystem.Lab.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Payment {
    @Id
    private String paymentid;
    private String name;
    private String cardno;
    private String cvc;
    private String date;
    private int payamount;

    public Payment(String paymentid, String name, String cardno, String cvc, String date, int payamount) {
        this.paymentid = paymentid;
        this.name = name;
        this.cardno = cardno;
        this.cvc = cvc;
        this.date = date;
        this.payamount = payamount;
    }



    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPayamount() {
        return payamount;
    }

    public void setPayamount(int payamount) {
        this.payamount = payamount;
    }




    }














