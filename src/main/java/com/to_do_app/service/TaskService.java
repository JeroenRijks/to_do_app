package com.to_do_app.service;


import com.to_do_app.model.Task;
import com.to_do_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Set<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        Optional<Task> b = taskRepository.findById(taskId);
        return b;
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

//    public Set<Task> getTasksBySearch(String name, String category, PriorityTypes importance, Date deadline){
//        return taskRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrImportanceContainingIgnoreCaseOrDeadlineContainingIgnoreCase(name, category, importance, deadline);
//    }
}
