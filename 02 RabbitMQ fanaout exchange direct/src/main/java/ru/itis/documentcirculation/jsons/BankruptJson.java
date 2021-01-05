package ru.itis.documentcirculation.jsons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankruptJson {

    private Long id;
    private Long currentMoney;
}
