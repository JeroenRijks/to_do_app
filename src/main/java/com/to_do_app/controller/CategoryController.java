package com.to_do_app.controller;


import com.to_do_app.model.Category;
import com.to_do_app.model.Task;
import com.to_do_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/{categoryId}")
    public @ResponseBody
    Category getCategoryById(@PathVariable(value = "categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId).get();
    }

    @PutMapping(path = "/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category category){
        category.setCategoryId(categoryId);
        return categoryService.addCategory(category);
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @Transactional // Do I need this?
    @DeleteMapping(path = "/delete/{categoryId}")
    // Used to be public void, but removed void because test can't return anything from a void
    public void deleteCategoryByCategoryId(@PathVariable(value = "categoryId") Long categoryId){
        categoryService.deleteCategoryByCategoryId(categoryId);
    // TODO: JJJ JEROEN Return deleted httpstatus stuff like Jack did.
    }

}
