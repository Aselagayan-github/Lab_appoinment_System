package com.LabSystem.Lab.pdf;

import com.LabSystem.Lab.Model.Result;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.io.ByteArrayOutputStream;

public class PDFGenerator {

    public static byte[] generatePDF(Result result) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Add content to the PDF
            document.add(new Paragraph("Appointment ID: " + result.getAppoinmentid()));
            document.add(new Paragraph("Name: " + result.getName()));
            document.add(new Paragraph("ID: " + result.getId()));
            document.add(new Paragraph("Gender: " + result.getGender()));
            document.add(new Paragraph("Contact Number: " + result.getContactno()));
            document.add(new Paragraph("Test Type: " + result.getTesttype()));
            document.add(new Paragraph("Result: " + result.getResult()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}

