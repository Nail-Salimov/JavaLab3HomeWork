package ru.itis.documentcirculation.entities.account;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long money;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private State state;
}
