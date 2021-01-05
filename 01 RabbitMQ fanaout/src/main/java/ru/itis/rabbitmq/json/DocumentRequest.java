package ru.itis.rabbitmq.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DocumentRequest {

    String surname;
    String name;
    String number;
    String date;
    String age;

}
