package io.ducnt.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ducnt.ecommerce.dtos.CategoryDto;
import io.ducnt.ecommerce.dtos.CreateCategoryDto;
import io.ducnt.ecommerce.services.CategoryService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
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
    void givenValidCreateCategoryInput_whenCreateCategory_thenReturnCreatedCategory() throws Exception {
        CreateCategoryDto dto = CreateCategoryDto.builder().categoryName("Book").description("Book").build();
        CategoryDto createdCategory = new CategoryDto(1, "Book", "Book", null);

        given(categoryService.createCategory(dto)).willReturn(createdCategory);

        ResultActions response = mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("categoryName", CoreMatchers.equalTo("Book")))
                .andExpect(jsonPath("description", CoreMatchers.equalTo("Book")));

    }
}