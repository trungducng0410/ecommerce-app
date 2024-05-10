package io.ducnt.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ducnt.ecommerce.dto.CreateCategoryDto;
import io.ducnt.ecommerce.service.CategoryService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    void givenValidCreateCategoryInput_whenCreateCategory_thenReturnCreatedMessage() throws Exception {
        CreateCategoryDto dto = CreateCategoryDto.builder().categoryName("Book").description("Book").build();

        given(categoryService.readCategory(any())).willReturn(null);
        willDoNothing().given(categoryService).createCategory(dto);

        ResultActions response = mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("success", CoreMatchers.is(true)));

    }
}