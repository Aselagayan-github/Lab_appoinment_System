package com.LabSystem.Lab.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data

public class Test {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Id
    int id;
    String name;

    public Test(int id, String name, String technician, String price) {
        this.id = id;
        this.name = name;
        this.technician = technician;
        this.price = price;
    }

    String technician;
    String price ;

}
