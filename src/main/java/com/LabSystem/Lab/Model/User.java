package com.LabSystem.Lab.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    String idno;
    String name;
    String address;
    String contactno;
    String usertype;
    String qulification;
    String experience;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
    public User(String idno, String name, String address, String contactno, String usertype, String qulification, String experience, String password) {
        this.idno = idno;
        this.name = name;
        this.address = address;
        this.contactno = contactno;
        this.usertype = usertype;
        this.qulification = qulification;
        this.experience = experience;
        this.password = password;
    }



    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getQulification() {
        return qulification;
    }

    public void setQulification(String qulification) {
        this.qulification = qulification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }






}
