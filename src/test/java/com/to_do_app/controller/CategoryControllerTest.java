package com.to_do_app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import cucumber.steps.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {


    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Autowired
    private MockMvc mockMvc;

    final private String endpoint = "/api/category";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private List<Category> testCategories = new ArrayList<>();

    private Category testCategory;

    private Category testCategory2;

    private Task testTask;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        testTask = new Task();
        testTask.setId(1L);  // The L is for the long data type
        testTask.setName("testTask");
        testTask.setImportance(PriorityTypes.LOW);
        testTask.setCategory(testCategory);
//        testTask.setDeadline(07/10/1996);  // Date class is deprecated

        testCategory = new Category();
        testCategory.setCategoryId(1L);
        testCategory.setName("testCategory");

        testCategory2 = new Category();
        testCategory2.setCategoryId(2L);
        testCategory2.setName("testCategory2");

        testCategories = Arrays.asList(testCategory, testCategory2);
        given(categoryService.getAllCategories()).willReturn(testCategories);
        given(categoryService.getCategoryById(anyLong())).willReturn(Optional.of(testCategory));
        given(categoryService.addCategory(any(Category.class))).willReturn(testCategory);
        given(categoryService.deleteCategoryByCategoryId(anyLong())).willReturn(Optional.of(testCategory));
        // TODO: Go through controller and service void stuff, return httpstatus
        // More givens here
    }

    @Test
    public void testGetAllCategoriesReturns200Response() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(endpoint + "/all"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String foundCategory = mvcResult.getResponse().getContentAsString();
        Category retrievedCategory = JsonUtil.toObjectFromJson(foundCategory, Category.class);
        assertEquals("testCategory",retrievedCategory.getName());

    }


}