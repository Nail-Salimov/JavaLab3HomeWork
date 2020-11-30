package ru.itis.mongohateoas.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Builder
public class User {

    @Id
    private String _id;
    private String name;
    private String surname;
    private Integer age;
    private String sex;

    @DBRef
    private List<Auto> auto;
    @DBRef
    private City city;
}
