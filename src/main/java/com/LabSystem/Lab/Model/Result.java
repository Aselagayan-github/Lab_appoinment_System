package com.LabSystem.Lab.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
public class Result {
    public String getAppoinmentid() {
        return Appoinmentid;
    }

    public void setAppoinmentid(String appoinmentid) {
        Appoinmentid = appoinmentid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public long getContactno() {
        return Contactno;
    }

    public void setContactno(long contactno) {
        Contactno = contactno;
    }

    public String getTesttype() {
        return Testtype;
    }

    public void setTesttype(String testtype) {
        Testtype = testtype;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    @Id
    String Appoinmentid;
    String Name;
    String Id;
    String Gender; // corrected field name
    long Contactno; // changed data type to long
    String Testtype;
    String Result;

    public Result(String appoinmentid, String name, String id, String gender, long contactno, String testtype, String result) {
        Appoinmentid = appoinmentid;
        Name = name;
        Id = id;
        Gender = gender;
        Contactno = contactno;
        Testtype = testtype;
        Result = result;
    }




}
