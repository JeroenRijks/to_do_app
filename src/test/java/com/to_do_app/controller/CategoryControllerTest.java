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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryServiceMock;

    @InjectMocks
    private CategoryController categoryController;

    @Autowired
    private MockMvc mockMvc;

    final private String endpoint = "/api/category";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private List<Category> testCategories = new ArrayList<>();
//    private List<Category> testCategories;

    private Category testCategory1;

    private Category testCategory2;

    private Category testCategoryInputData;

    private Task testTask;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        testTask = new Task();
        testTask.setTaskId(1L);  // The L is for the long data type
        testTask.setName("testTask");
        testTask.setImportance(PriorityTypes.LOW);
        testTask.setCategory(testCategory1);
//        testTask.setDeadline(07/10/1996);  // Date class is deprecated

        testCategory1 = new Category();
        testCategory1.setCategoryId(1L);
        testCategory1.setName("testCategory1");

        testCategory2 = new Category();
        testCategory2.setCategoryId(2L);
        testCategory2.setName("testCategory2");

        testCategoryInputData = new Category();
        testCategoryInputData.setCategoryId(3L);
        testCategoryInputData.setName("inputDataName");

        List testCategories = new ArrayList();
        testCategories.add(testCategory1);
        testCategories.add(testCategory2);

        given(categoryServiceMock.getAllCategories()).willReturn(testCategories);
        given(categoryServiceMock.getCategoryById(anyLong())).willReturn(Optional.of(testCategory1));
        given(categoryServiceMock.saveCategory(any(Category.class))).willReturn(testCategory1);
//        given(categoryServiceMock.deleteCategoryById(anyLong())).willReturn(Optional.of(testCategory1));
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
        assertEquals("testCategory1", actualCategory1.getName());
        assertEquals((Long) 1L, actualCategory1.getCategoryId());
        assertEquals("testCategory2", actualCategory2.getName());
        assertEquals((Long) 2L, actualCategory2.getCategoryId());
        verify(categoryServiceMock, times(1)).getAllCategories();
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testGetCategoryByIdReturns200Response() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(endpoint + "/" + 1L))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String foundCategory = mvcResult.getResponse().getContentAsString();
        Category actualCategory1 = MAPPER.readValue(foundCategory, Category.class);
        assertEquals((Long) 1L, actualCategory1.getCategoryId());
        assertEquals( "testCategory1", actualCategory1.getName());
        verify(categoryServiceMock, times(1)).getCategoryById(1L);
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testGetCategoryByIdReturnsNotFoundResponseIfNotFound() throws Exception {
        // This given simulates returning nothing
        given(categoryServiceMock.getCategoryById(anyLong())).willReturn(Optional.empty());
        MvcResult mvcResult = mockMvc.perform(get(endpoint + "/" + 4L))
                .andExpect(status().is4xxClientError())
                .andReturn();
        verify(categoryServiceMock, times(1)).getCategoryById(4L);
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testPutCategoryByIdReturns200Response() throws Exception {
        String testJsonInputData = JsonUtil.toJson(testCategoryInputData);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(endpoint + "/" + 1L)
                .content(testJsonInputData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Category objectFromController = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals("inputDataName", objectFromController.getName());
        verify(categoryServiceMock, times(1)).saveCategory(any(Category.class));
        verify(categoryServiceMock, times(1)).getCategoryById(1L);
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testPutCategoryByIdReturnsNotFoundResponseIfNotFound() throws Exception {
        // This given simulates returning nothing
        given(categoryServiceMock.getCategoryById(anyLong())).willReturn(Optional.empty());
        String testJsonInputData = JsonUtil.toJson(testCategoryInputData);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(endpoint + "/" + 4L)
                .content(testJsonInputData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
        verify(categoryServiceMock, times(1)).getCategoryById(4L);
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testPostCategoryReturns200Response() throws Exception {
        // Overwrites setup - returns the input data.
        given(categoryServiceMock.saveCategory(any(Category.class))).willReturn(testCategoryInputData);
        String testJsonInputData = JsonUtil.toJson(testCategoryInputData);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/")
                .content(testJsonInputData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Category objectFromController = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals("inputDataName", objectFromController.getName());
        // reqCategory (the saved item) is not within the scope of this test, so verify save(any category)
        verify(categoryServiceMock, times(1)).saveCategory(any(Category.class));
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testDeleteCategoryReturns200Response() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete(endpoint + "/" + 1L))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Category objectFromController = MAPPER.readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals("testCategory1", objectFromController.getName());
        assertEquals((Long) 1L, objectFromController.getCategoryId());
        verify(categoryServiceMock, times(1)).getCategoryById(1L);
        verify(categoryServiceMock, times(1)).deleteCategoryById(1L);
        verifyNoMoreInteractions(categoryServiceMock);
    }

    @Test
    public void testDeleteCategoryReturnsNotFoundResponseIfNotFound() throws Exception {
        given(categoryServiceMock.getCategoryById(anyLong())).willReturn(Optional.empty());
        MvcResult mvcResult = mockMvc.perform(delete(endpoint + "/" + 4L))
                .andExpect(status().is4xxClientError())
                .andReturn();
        verify(categoryServiceMock, times(1)).getCategoryById(4L);
        verifyNoMoreInteractions(categoryServiceMock);
    }


}