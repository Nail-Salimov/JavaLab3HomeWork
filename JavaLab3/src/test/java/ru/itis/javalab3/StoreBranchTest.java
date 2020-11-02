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
import ru.itis.javalab3.entities.StoreBranch;
import ru.itis.javalab3.entities.StoreBranchState;
import ru.itis.javalab3.services.StoreBranchService;

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
public class StoreBranchTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreBranchService storeBranchService;

    @BeforeEach
    public void setUp(){
        when(storeBranchService.close(1L)).thenReturn(closeStoreBranch());
    }

    @Test
    public void closeStoreBranchTest() throws Exception{
        mockMvc.perform(put("/store_branch/1/close")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(closeStoreBranch().getAddress()))
                .andExpect(jsonPath("$.state").value(closeStoreBranch().getState().toString()))
                .andDo(document("close_store_branch", responseFields(
                        fieldWithPath("id").description("Id магазина"),
                        fieldWithPath("address").description("Описание магазина"),
                        fieldWithPath("state").description("Состояние магазина"),
                        fieldWithPath("localProductSet").description("Список продуктов в данном отделении магазина")
                )));
    }

    private StoreBranch closeStoreBranch(){
        return StoreBranch.builder()
                .state(StoreBranchState.CLOSE)
                .address("Some address")
                .id(1L)
                .build();
    }

}
