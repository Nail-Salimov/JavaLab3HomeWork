package ru.itis.documentcirculation.documentcreator;

import ru.itis.documentcirculation.jsons.BankruptJson;
import ru.itis.documentcirculation.jsons.OpenAccountJson;

public interface OpenAccountDocumentCreator {
    void createDocument(OpenAccountJson json, Long number);
}
