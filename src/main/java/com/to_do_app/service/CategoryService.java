package com.to_do_app.service;


import com.to_do_app.model.Category;
import com.to_do_app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Set<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId){
        return categoryRepository.findById(categoryId);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryByCategoryId(Long categoryId){
        categoryRepository.deleteById(categoryId);
        // TODO: VOID STUFF
    }

}
