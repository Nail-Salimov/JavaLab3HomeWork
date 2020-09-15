package ru.itis.rabbitmq.consumers.callbacks.forms;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.rabbitmq.json.DocumentRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class VocationDeliverCallback implements DeliverCallback {

    private ObjectMapper objectMapper;
    private String src;
    private String dest;
    private String russianFont;

    public VocationDeliverCallback() {
        this.objectMapper = new ObjectMapper();
    }

    public VocationDeliverCallback(String src, String dest, String russianFont) {
        objectMapper = new ObjectMapper();
        this.src = src;
        this.dest = dest;
        this.russianFont = russianFont;
    }

    @Override
    public void handle(String consumerTag, Delivery delivery) throws IOException {

        String message = new String(delivery.getBody(), "UTF-8");
        DocumentRequest documentRequest = objectMapper.readValue(message, DocumentRequest.class);
        createPdf(documentRequest);
    }

    public void createPdf(DocumentRequest documentRequest) {
        try {

            String actualPath = dest + createFileName(documentRequest);
            File file = new File(actualPath);

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(actualPath));
            Document document = new Document(pdfDoc);
            PdfFont russian = PdfFontFactory.createFont(russianFont, "CP1251", true);

            document.setFont(russian);

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            Map<String, PdfFormField> fields = form.getFormFields();

            fields.get("surname").setValue(documentRequest.getSurname()).setReadOnly(true).setFontSize(13);
            fields.get("name").setValue(documentRequest.getName()).setReadOnly(true).setFontSize(13);
            fields.get("age").setValue(documentRequest.getAge()).setReadOnly(true).setFontSize(13);
            fields.get("date").setValue(documentRequest.getDate()).setReadOnly(true).setFontSize(13);
            fields.get("number").setValue(documentRequest.getNumber()).setReadOnly(true).setFontSize(13);

            pdfDoc.close();
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    public String createFileName(DocumentRequest documentRequest) {
        return "vocation_" + documentRequest.getSurname() + "_" + documentRequest.getName() + "_" + UUID.randomUUID().toString() + ".pdf";
    }
}
