package com.to_do_app.repository;

import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import org.springframework.data.repository.CrudRepository;
import com.to_do_app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository // Attaches "findById" (a service) to the Task model.
public interface TaskRepository extends CrudRepository<Task, Long> {
    Set<Task> findAll();

    Set<Task> findAllByCategory(Category category);

    Set<Task> findAllByImportance(PriorityTypes importance);

    void deleteById(Long taskId);

}
