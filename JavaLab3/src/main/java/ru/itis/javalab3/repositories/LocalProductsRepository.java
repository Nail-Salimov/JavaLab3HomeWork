package ru.itis.javalab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab3.entities.LocalProduct;

import java.util.List;
import java.util.Optional;


public interface LocalProductsRepository extends JpaRepository<LocalProduct, Long> {

    @Query("SELECT l FROM LocalProduct l WHERE l.product.id in ?1")
    List<LocalProduct> findAllByProductsId(Iterable<Long> productsId);
}
