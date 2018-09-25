package com.to_do_app.service;


import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @InjectMocks
    private CategoryService categoryService;

    private Category testCategory1;
    private Category testCategory2;
    private Task testTask;
    private List<Category> testCategories;

    @Before
    public void setup() {
        testCategory1 = new Category();
        testCategory1.setCategoryId(1L);
        testCategory1.setName("testCategory1");

        testCategory2 = new Category();
        testCategory2.setCategoryId(2L);
        testCategory2.setName("testCategory2");

        testTask = new Task();
        testTask.setId(1L);  // The L is for the long data type
        testTask.setName("testTask");
        testTask.setImportance(PriorityTypes.LOW);
        testTask.setCategory(testCategory1);

        testCategories = Arrays.asList(testCategory1,testCategory2);

        given(categoryRepositoryMock.findAll()).willReturn(testCategories);
        given(categoryRepositoryMock.findById(1L)).willReturn(Optional.of(testCategory1));
        given(categoryRepositoryMock.findById(2L)).willReturn(Optional.of(testCategory2));
        given(categoryRepositoryMock.save(any(Category.class))).willReturn(testCategory1);
    }

    @Test
    public void testGetCategoryByName(){
        Optional<Category> fetchedCategory = categoryService.getCategoryById(1L);
        assertEquals(testCategory1,fetchedCategory.get());
        verify(categoryRepositoryMock,times(1)).findById(1L);
        verifyNoMoreInteractions(categoryRepositoryMock);
    }

    @Test
    public void testGetAllCategories(){
        List<Category> fetchedCategories = categoryService.getAllCategories();
        assertEquals(testCategories,fetchedCategories);
        verify(categoryRepositoryMock,times(1)).findAll();
        verifyNoMoreInteractions(categoryRepositoryMock);
    }

    @Test
    public void testSaveCategory(){
        Category newCategory = categoryService.saveCategory(testCategory1);
        assertEquals(testCategory1, newCategory);
        verify(categoryRepositoryMock,times(1)).save(testCategory1);
        verifyNoMoreInteractions(categoryRepositoryMock);
    }

    @Test
    public void testDeleteCategoryByCategoryId(){
        ResponseEntity responseEntity = categoryService.deleteCategoryByCategoryId(1L);
        assertEquals(responseEntity,ResponseEntity.ok().build());
        verify(categoryRepositoryMock,times(1)).deleteById(1L);
        verifyNoMoreInteractions(categoryRepositoryMock);
    }

}
