package ru.itis.javalab3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab3.entities.DiscountCard;

public interface DiscountCardsRepository extends JpaRepository<DiscountCard, Long> {
}
