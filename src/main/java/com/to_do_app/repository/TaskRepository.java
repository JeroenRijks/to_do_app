package com.to_do_app.repository;

import com.to_do_app.model.PriorityTypes;
import org.springframework.data.repository.CrudRepository;
import com.to_do_app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Set<Task> findAll();
    Task findById(Integer id);
    void deleteById(Integer id);
    Set<Task> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrImportanceContainingIgnoreCaseOrDeadlineContainingIgnoreCase(String name, String category, PriorityTypes importance, Date deadline);
}
