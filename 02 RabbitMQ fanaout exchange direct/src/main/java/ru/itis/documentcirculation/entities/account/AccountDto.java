package ru.itis.documentcirculation.entities.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountDto {

    private Long id;
    private Long money;
    private State state;

}
