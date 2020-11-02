package ru.itis.javalab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab3.entities.Transaction;

import java.util.List;

@RepositoryRestResource
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @RestResource(path = "returnedTransactions", rel = "returnedTransactions")
    @Query("SELECT t FROM Transaction t WHERE t.state = 1")
    List<Transaction> returnedTransactions();
}
