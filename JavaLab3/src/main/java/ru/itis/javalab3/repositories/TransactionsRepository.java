package ru.itis.javalab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab3.entities.Transaction;


public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
