package com.to_do_app.service;


import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {
//    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getCategoryFilteredTasks(Category filteringCategory) {
        return taskRepository.findAllByCategory(filteringCategory);
    }

    public List<Task> getImportanceFilteredTasks(PriorityTypes importance) {
        return taskRepository.findAllByImportance(importance);
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
