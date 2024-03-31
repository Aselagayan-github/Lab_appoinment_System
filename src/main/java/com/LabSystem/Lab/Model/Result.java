package com.LabSystem.Lab.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@Getter
@Setter
public class Result {


    @Id
    private String appoinmentid;
    private String name;
    private String id;
    private String gender;
    private long contactno;
    private String testtype;
    private String result;

    // Default constructor
    public Result() {
    }

    public Result(String appoinmentid, String name, String id, String gender, long contactno, String testtype, String result) {
        this.appoinmentid = appoinmentid;
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.contactno = contactno;
        this.testtype = testtype;
        this.result = result;
    }

    // Additional constructor to handle validation without contactno
    public Result(String appoinmentid, String name, String id, String gender, String testtype, String result) {
        this.appoinmentid = appoinmentid;
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.testtype = testtype;
        this.result = result;
    }


}
