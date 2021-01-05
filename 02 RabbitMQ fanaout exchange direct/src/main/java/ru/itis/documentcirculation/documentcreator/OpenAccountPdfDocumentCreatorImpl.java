package ru.itis.documentcirculation.documentcreator;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.jsons.BankruptJson;
import ru.itis.documentcirculation.jsons.OpenAccountJson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class OpenAccountPdfDocumentCreatorImpl implements OpenAccountDocumentCreator {

    @Value("${open.pdf.src}")
    private String src;

    @Value("${open.pdf.dest}")
    private String dest;

    @Value("${open.pdf.font}")
    private String font;


    @Override
    public void createDocument(OpenAccountJson json, Long number) {
        createPdf(json, number);
    }

    private void createPdf(OpenAccountJson json, Long number) {
        try {

            String actualPath = dest + createFileName(json);
            File file = new File(actualPath);

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(actualPath));
            Document document = new Document(pdfDoc);
            PdfFont russian = PdfFontFactory.createFont(font, "CP1251", true);

            document.setFont(russian);

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            Map<String, PdfFormField> fields = form.getFormFields();

            fields.get("surname").setValue(json.getSurname()).setReadOnly(true).setFontSize(13);
            fields.get("name").setValue(json.getName()).setReadOnly(true).setFontSize(13);
            fields.get("currency").setValue(json.getCurrency()).setReadOnly(true).setFontSize(13);
            fields.get("number").setValue(String.valueOf(number)).setReadOnly(true).setFontSize(13);

            pdfDoc.close();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String createFileName(OpenAccountJson json) {
        return "open_" + json.getSurname() + "_" + json.getName() + "_" + UUID.randomUUID().toString() + ".pdf";
    }
}
