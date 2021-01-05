package ru.itis.documentcirculation.jsons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OpenAccountJson {

    private String surname;
    private String name;
    private String currency;
}
