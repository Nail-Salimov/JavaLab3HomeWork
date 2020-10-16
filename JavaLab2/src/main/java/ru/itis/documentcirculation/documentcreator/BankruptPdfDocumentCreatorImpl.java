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

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class BankruptPdfDocumentCreatorImpl implements BankruptDocumentCreator {

    @Value("${bankrupt.pdf.src}")
    private String src;

    @Value("${bankrupt.pdf.dest}")
    private String dest;

    @Value("${bankrupt.pdf.font}")
    private String font;

    @Override
    public void createDocument(BankruptJson json) {
        createPdf(json);
    }

    private void createPdf(BankruptJson json){
        try {

            String actualPath = dest + createFileName(json);
            File file = new File(actualPath);

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(actualPath));
            Document document = new Document(pdfDoc);
            PdfFont russian = PdfFontFactory.createFont(font, "CP1251", true);

            document.setFont(russian);

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
            Map<String, PdfFormField> fields = form.getFormFields();

            fields.get("number").setValue(String.valueOf(json.getId())).setReadOnly(true).setFontSize(13);
            fields.get("money").setValue(String.valueOf(json.getCurrentMoney())).setReadOnly(true).setFontSize(13);

            pdfDoc.close();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String createFileName(BankruptJson bankruptJson) {
        return "bankrupt_" + bankruptJson.getId() + "_" + UUID.randomUUID().toString() + ".pdf";
    }
}
