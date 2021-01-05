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
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.entities.Product;
import ru.itis.javalab3.entities.StoreBranch;
import ru.itis.javalab3.entities.StoreBranchState;
import ru.itis.javalab3.services.LocalProductService;

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
public class LocalProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalProductService localProductService;

    @BeforeEach
    public void setUp() {
        when(localProductService.buyLocalProduct(1L)).thenReturn(buyLocalProduct());
    }

    @Test
    public void buyLocalProductTest() throws Exception {
        mockMvc.perform(put("/localProducts/1/buy")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(String.valueOf(buyLocalProduct().getCount())))
                .andDo(document("buy_local_product", responseFields(
                        fieldWithPath("id").description("Id локального продукта"),
                        fieldWithPath("count").description("Количество товара"),
                        fieldWithPath("storeBranch.id").description("Id магазина"),
                        fieldWithPath("storeBranch.state").description("Состояние магазина"),
                        fieldWithPath("storeBranch.address").description("Адрес магазина"),
                        fieldWithPath("storeBranch.localProductSet").description("Список товаров в магазине"),
                        fieldWithPath("product.id").description("Id товара"),
                        fieldWithPath("product.transactionSet").description("Список покупок с этим товаром"),
                        fieldWithPath("product.price").description("Цена товара"),
                        fieldWithPath("product.localProductSet").description("Список всех локальных продуктов")
                )));
    }

    private LocalProduct buyLocalProduct() {
        Product product = Product.builder()
                .id(1L)
                .price(1000)
                .build();

        StoreBranch storeBranch = StoreBranch.builder()
                .id(1L)
                .address("Some address")
                .state(StoreBranchState.OPEN)
                .build();

        return LocalProduct.builder()
                .count(100L)
                .id(1L)
                .storeBranch(storeBranch)
                .product(product)
                .build();
    }
}
