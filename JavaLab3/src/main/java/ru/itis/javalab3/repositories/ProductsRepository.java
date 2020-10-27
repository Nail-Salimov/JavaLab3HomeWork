package ru.itis.javalab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab3.entities.Product;

import java.util.List;


@RepositoryRestResource
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @RestResource(path = "location", rel = "location")
    @Query("select p from Product p WHERE p.price < :price")
    List<Product> getLessThan(@Param("price") Integer price);

}
