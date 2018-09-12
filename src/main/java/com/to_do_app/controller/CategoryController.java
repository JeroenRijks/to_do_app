package com.to_do_app.controller;


import com.to_do_app.model.Category;
import com.to_do_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        System.out.println("get method called in category controller");
        return categoryService.getCategoryById(categoryId).get();
    }

    @PutMapping(path = "/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category category){
        System.out.println("put method called in category controller");
        Optional<Category> savedCategory = categoryService.getCategoryById(categoryId);
        if (savedCategory.isPresent()) {
            System.out.println("JJJ : Category found");
        } else {
            System.out.println("JJJ : Category not found");
        }

        category.setCategoryId(categoryId);
        return categoryService.saveCategory(category);
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @DeleteMapping(path = "/{categoryId}")
    public void deleteCategoryById(@PathVariable(value = "categoryId") Long categoryId){
        System.out.println("Delete called in controller");
        categoryService.deleteCategoryByCategoryId(categoryId);
    }


}
