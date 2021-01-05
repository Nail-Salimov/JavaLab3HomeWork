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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "product")
    private Set<LocalProduct> localProductSet;

    @ManyToMany(mappedBy = "productSet")
    private Set<Transaction> transactionSet;

}
