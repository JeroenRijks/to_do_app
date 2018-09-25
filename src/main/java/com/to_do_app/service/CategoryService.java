package com.to_do_app.service;


import com.to_do_app.model.Category;
import com.to_do_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public ResponseEntity deleteCategoryByCategoryId(Long categoryId){
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.ok().build();
        // TODO: VOID STUFF
    }

}
