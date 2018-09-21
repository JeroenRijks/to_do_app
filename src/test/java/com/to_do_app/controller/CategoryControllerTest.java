package com.to_do_app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.service.CategoryService;
import cucumber.steps.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.*;

import static com.sun.xml.internal.ws.api.ComponentFeature.Target.ENDPOINT;
import static cucumber.steps.JsonUtil.toJson;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    private Category testCategoryInputData;

    private Task testTask;


    @Before
    public void setup() {
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

        testCategoryInputData = new Category();
        testCategoryInputData.setCategoryId(1L);
        testCategoryInputData.setName("UpdatedName");

        List testCategories = new ArrayList();
        testCategories.add(testCategory);
        testCategories.add(testCategory2);

        given(categoryService.getAllCategories()).willReturn(testCategories);
        given(categoryService.getCategoryById(anyLong())).willReturn(Optional.of(testCategory));
        given(categoryService.saveCategory(any(Category.class))).willReturn(testCategory);
//        given(categoryService.deleteCategoryByCategoryId(anyLong())).willReturn(Optional.of(testCategory));
        // TODO: Go through controller and service void stuff, return httpstatus
        // More givens here
    }

    @Test
    public void testGetAllCategoriesReturns200Response() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get(endpoint + "/"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String foundCategory = mvcResult.getResponse().getContentAsString();
        List<Category> actualCategories = MAPPER.readValue(foundCategory, MAPPER.getTypeFactory().constructCollectionType(List.class, Category.class));
        Category actualCategory1 = actualCategories.get(0);
        Category actualCategory2 = actualCategories.get(1);
        assertEquals("testCategory", actualCategory1.getName());
        assertEquals((Long) 1L, actualCategory1.getCategoryId());
        assertEquals("testCategory2", actualCategory2.getName());
        assertEquals((Long) 2L, actualCategory2.getCategoryId());
    }

    @Test
    public void testGetCategoryByIdReturns200Response() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(endpoint + "/" + 1L))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String foundCategory = mvcResult.getResponse().getContentAsString();
        Category actualCategory1 = MAPPER.readValue(foundCategory, Category.class);
        assertEquals((Long) 1L, actualCategory1.getCategoryId());
        assertEquals( "testCategory", actualCategory1.getName());
    }

    @Test
    public void testGetCategoryByIdReturnsNotFoundResponseIfNotFound() throws Exception {
        given(categoryService.getCategoryById(anyLong())).willReturn(Optional.empty());
        MvcResult mvcResult = mockMvc.perform(get(endpoint + "/" + 4L))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    // Check to find the error message?

//    @Test
//    public void testPutCategoryByIdReturns200Response() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(put(endpoint + "/" + 1L))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//        String found
//
//    }

    @Test
    public void testPutCategoryByIdReturns200Response() throws Exception {
        String testJsonInputData = JsonUtil.toJson(testCategoryInputData);
        System.out.println("JJJ");
        System.out.println(testJsonInputData);
        System.out.println("JJJ");
//        System.out.println(testCategories.get(0).getName());
//        System.out.println("JJJ");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(endpoint + "/" + 1L)
                .content(testJsonInputData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Category category = JsonUtil.toObjectFromJson(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals("UpdatedName", category.getName());
    }





//
//    @Test
//    public void testPutCategoryByIdReturnsNotFoundResponseIfNotFound() throws Exception {}
//
//    @Test
//    public void testPostCategoryReturns200Response() throws Exception {}
//
//    @Test
//    public void testPostCategoryReturns200Response() throws Exception {}
//
//    @Test
//    public void testDeleteCategoryReturns200Response() throws Exception {}
//
//    @Test
//    public void testDeleteCategoryReturnsNotFoundResponseIfNotFound() throws Exception {}



}