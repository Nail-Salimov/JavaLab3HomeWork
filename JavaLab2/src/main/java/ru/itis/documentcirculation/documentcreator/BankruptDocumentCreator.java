package ru.itis.documentcirculation.documentcreator;

import ru.itis.documentcirculation.jsons.BankruptJson;

public interface BankruptDocumentCreator {

    void createDocument(BankruptJson json);
}
