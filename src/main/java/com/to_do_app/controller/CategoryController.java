package com.to_do_app.controller;


import com.to_do_app.model.Category;
import com.to_do_app.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "")
    public ResponseEntity getAllCategories(){
        List<Category> fetchedCategories = categoryService.getAllCategories();
        if(fetchedCategories.isEmpty()){
            return new ResponseEntity<>(new Message("The category list is empty."),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(fetchedCategories,HttpStatus.ACCEPTED);
    }






    @GetMapping(path = "/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable(value = "categoryId") Long categoryId){
        Optional<Category> fetchedCategory;
        fetchedCategory = categoryService.getCategoryById(categoryId);
        if (!fetchedCategory.isPresent()){
            return new ResponseEntity<>(new Message("Cannot get this category because it does not exist."),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fetchedCategory.get(),HttpStatus.ACCEPTED);    }








    @PutMapping(path = "/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category reqCategory){
        Optional<Category> fetchedCategory;
        fetchedCategory = categoryService.getCategoryById(categoryId);
        if (!fetchedCategory.isPresent()){
            return new ResponseEntity(new Message("Cannot edit this category because it does not exist."),HttpStatus.NOT_FOUND);
        }
        Category updatedCategory = fetchedCategory.get();

        if(reqCategory.getName() != null) {
            updatedCategory.setName(reqCategory.getName());
        }
        if(reqCategory.getTask() != null) {
            updatedCategory.setTask(reqCategory.getTask());
        }
        categoryService.saveCategory(updatedCategory);
        return new ResponseEntity(updatedCategory,HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "")
    public ResponseEntity postCategory(@RequestBody Category reqCategory){
        categoryService.saveCategory(reqCategory);
        return new ResponseEntity(reqCategory,HttpStatus.CREATED);
    }

    @Transactional // Do I need this?
    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity deleteCategoryById(@PathVariable(value = "categoryId") Long categoryId){
        System.out.println("JJJ Delete was called with path variable " + categoryId);
        Optional<Category> fetchedCategory;
        fetchedCategory = categoryService.getCategoryById(categoryId);
        if(!fetchedCategory.isPresent()){
            return new ResponseEntity(new Message("Cannot delete this category because it does not exist."),HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity(fetchedCategory,HttpStatus.ACCEPTED);
    }

}
