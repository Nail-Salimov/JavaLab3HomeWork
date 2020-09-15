package ru.itis.rabbitmq.consumers.callbacks.coordinates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import ru.itis.rabbitmq.json.DocumentRequest;
import java.io.File;
import java.io.IOException;
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
        this.objectMapper = new ObjectMapper();
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


            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            PdfFont russian = PdfFontFactory.createFont(russianFont, "CP1251", true);
            document.setFont(russian);

            document.add(new Paragraph(documentRequest.getSurname()).setFixedPosition(1, 180, 716, 100).setFontSize(13));
            document.add(new Paragraph(documentRequest.getName()).setFixedPosition(1, 180, 691, 100).setFontSize(13));
            document.add(new Paragraph(documentRequest.getNumber()).setFixedPosition(1, 180, 667, 100).setFontSize(13));
            document.add(new Paragraph(documentRequest.getAge()).setFixedPosition(1, 180, 643, 100).setFontSize(13));
            document.add(new Paragraph(documentRequest.getDate()).setFixedPosition(1, 180, 619, 100).setFontSize(13));

            document.close();
            pdfDoc.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String createFileName(DocumentRequest documentRequest) {
        return "vocation_" + documentRequest.getSurname() + "_" + documentRequest.getName() + "_" + UUID.randomUUID().toString() + ".pdf";
    }
}
