package ru.itis.javalab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.javalab3.entities.Customer;
import ru.itis.javalab3.entities.Transaction;
import ru.itis.javalab3.entities.TransactionState;
import ru.itis.javalab3.services.TransactionsService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class TransactionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionsService transactionsService;

    @BeforeEach
    public void setUp() {
        when(transactionsService.returnPurchase(1L)).thenReturn(purchaseTransaction());

    }

    @Test
    public void purchaseTransactionTest() throws Exception {

        mockMvc.perform(put("/transactions/1/return")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value(purchaseTransaction().getState().toString()))
                .andDo(document("return_transaction", responseFields(
                        fieldWithPath("state").description("Состояние транзакции"),
                        fieldWithPath("id").description("Id"),
                        fieldWithPath("customer.id").description("Id Покупателя"),
                        fieldWithPath("customer.surname").description("Фамилия покупателя"),
                        fieldWithPath("customer.name").description("Имя покупателя"),
                        fieldWithPath("customer.discountCard").description("Карта покупателя"),
                        fieldWithPath("customer.transactionSet").description("Все покупки покупателя"),
                        fieldWithPath("productSet").description("Список товаров")
                )));
    }

    private Transaction purchaseTransaction() {
        Customer customer = Customer.builder().name("Name").surname("Surname").build();
        return Transaction.builder().id(1L).customer(customer).state(TransactionState.NOT_RETURNED).build();
    }

}
