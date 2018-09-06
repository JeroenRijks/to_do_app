package com.to_do_app.service;


import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Set<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

//    public Set<Task> getTasksBySearch(String name, String category, PriorityTypes importance, Date deadline){
//        return taskRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrImportanceContainingIgnoreCaseOrDeadlineContainingIgnoreCase(name, category, importance, deadline);
//    }
}
