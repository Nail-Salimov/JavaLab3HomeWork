package ru.itis.dsl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String _id;
    private String name;
    private String surname;
    private Integer age;
    private String sex;

}
