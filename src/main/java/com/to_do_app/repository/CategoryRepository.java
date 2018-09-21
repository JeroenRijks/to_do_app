package com.to_do_app.repository;

import com.to_do_app.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findAll();
    Optional<Category> findById(Long categoryId);
}
