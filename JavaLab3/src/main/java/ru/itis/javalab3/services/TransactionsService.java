package ru.itis.javalab3.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab3.entities.Transaction;

import java.util.List;

public interface TransactionsService {

    Transaction createTransaction(List<Long> productsId, Long storeBranchId, Long customerId);
    Transaction returnPurchase(Long transactionId);

}
