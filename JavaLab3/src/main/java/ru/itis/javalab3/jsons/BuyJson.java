package ru.itis.javalab3.jsons;

import lombok.*;
import ru.itis.javalab3.entities.StoreBranch;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyJson {

    private Long customerId;

    private Long storeBranchId;

    private List<Long> productsIdList;
}
