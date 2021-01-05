package ru.itis.javalab3.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "customer")
    private DiscountCard discountCard;

    @OneToMany(mappedBy = "customer")
    private Set<Transaction> transactionSet;
}
