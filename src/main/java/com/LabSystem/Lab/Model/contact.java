package com.LabSystem.Lab.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
@Data
@NoArgsConstructor

public class contact {

    String name;
    int id;
    String email;
    String subject;
    String message;

    public contact(String name, int id, String email, String subject, String message) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }


}
