package com.to_do_app.repository;

import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@Repository // Attaches "findById" (a service) to the Task model.
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

    List<Task> findAllByCategory(Category category);

    List<Task> findAllByImportance(PriorityTypes importance);

    void deleteById(Long taskId);

}
