package ru.itis.javalab3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab3.entities.*;
import ru.itis.javalab3.repositories.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService{

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private LocalProductsRepository localProductsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private StoreBranchesRepository storeBranchesRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public Transaction createTransaction(List<Long> productsId, Long storeBranchId, Long customerId) {

        Optional<Customer> customerOptional = customersRepository.findById(customerId);
        if(!customerOptional.isPresent()){
            throw new IllegalArgumentException("customer not exist with id = " + customerId);
        }

        List<Product> productList = productsRepository.findAllById(productsId);

        if(productList.size() != productsId.size()){
            throw new IllegalArgumentException("Some products id not exist");
        }

        Optional<StoreBranch> storeBranchOptional = storeBranchesRepository.findById(storeBranchId);
        if (!storeBranchOptional.isPresent()){
            throw new IllegalArgumentException("StoreBranch with id = " + storeBranchId + " not exist");
        }

        List<LocalProduct> localProductList =  localProductsRepository.findAllByProductsId(productsId);
        if(localProductList.size() != productsId.size()){
            throw new IllegalArgumentException("Some local products id not exist");
        }

        for (LocalProduct localProduct : localProductList){
            if (localProduct.getCount() < 1){
                throw new IllegalArgumentException("Local product id = " + localProduct.getId() + " not contain");
            }
            localProduct.setCount(localProduct.getCount() - 1);
        }

        Transaction transaction = Transaction.builder()
                .customer(customerOptional.get())
                .productSet(new HashSet<>(productList))
                .build();

        transaction = transactionsRepository.save(transaction);
        localProductsRepository.saveAll(localProductList);
        return transaction;

    }

    @Override
    public Transaction returnPurchase(Long transactionId) {
        Optional<Transaction> optionalTransaction = transactionsRepository.findById(transactionId);
        if (!optionalTransaction.isPresent()){
            throw new IllegalArgumentException("transaction with id not found");
        }

        Transaction transaction = optionalTransaction.get();
        if (transaction.getState().equals(TransactionState.RETURNED)){
            throw new IllegalArgumentException("transaction is already RETURNED");
        }
        transaction.setState(TransactionState.RETURNED);
        return transactionsRepository.save(transaction);
    }
}
