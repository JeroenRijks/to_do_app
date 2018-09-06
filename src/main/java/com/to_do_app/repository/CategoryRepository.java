package com.to_do_app.repository;

import com.to_do_app.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Set<Category> findAll();
    Category findById(Integer id);
    Category deleteById(Integer id);

}
