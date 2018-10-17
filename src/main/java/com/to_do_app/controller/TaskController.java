package com.to_do_app.controller;

import com.to_do_app.model.Category;
import com.to_do_app.model.PriorityTypes;
import com.to_do_app.model.Task;
import com.to_do_app.service.TaskService;
import com.to_do_app.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.xml.ws.Service;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path="/api/task")   // the base url for tasks
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "")
    public ResponseEntity getAllTasks(){
        Set<Task> fetchedTasks = taskService.getAllTasks();
        if(fetchedTasks.isEmpty()){
            return new ResponseEntity<>(new Message("The task list is empty."),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(fetchedTasks,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{taskId}")
    public ResponseEntity getTaskById(@PathVariable(value = "taskId") Long taskId) {
        Optional<Task> fetchedTask;
        fetchedTask = taskService.getTaskById(taskId);
        if (!fetchedTask.isPresent()){
            return new ResponseEntity(new Message("Cannot get this task because it does not exist."),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(fetchedTask.get(),HttpStatus.ACCEPTED); //if statement to return message if the taskId doesn't exist.
    }

    @GetMapping(path = "/filterByCategory/{categoryId}")
    public ResponseEntity filterTaskByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        Optional<Category> filteringCategory;
        filteringCategory = categoryService.getCategoryById(categoryId);
        if(!filteringCategory.isPresent()) {
            return new ResponseEntity<>(new Message("The category you are filtering by does not exist"), HttpStatus.NOT_FOUND);
        } else {
            Set<Task> categoryFilteredTasks = taskService.getCategoryFilteredTasks(filteringCategory.get());
            return new ResponseEntity<>(categoryFilteredTasks, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping(path = "/filterByImportance/{importance}")
    public ResponseEntity filterTaskByImportance(@PathVariable(value = "importance") PriorityTypes importance) {
        Set<Task> importanceFilteredTasks = taskService.getImportanceFilteredTasks(importance);
        return new ResponseEntity<>(importanceFilteredTasks, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{taskId}")
    public ResponseEntity updateTask(@PathVariable(value = "taskId") Long taskId, @RequestBody Task reqTask) {
        Optional<Task> fetchedTask;
        fetchedTask = taskService.getTaskById(taskId);
        if (!fetchedTask.isPresent()){
            return new ResponseEntity(new Message("Cannot edit this task because it doesn't exist."),HttpStatus.NOT_FOUND);
        }
        Task newTask = fetchedTask.get();

        // Only update the fields that have new info.
        if(reqTask.getName() != null) {
            newTask.setName(reqTask.getName());
        }
        if(reqTask.getCategory() != null) {
            newTask.setCategory(reqTask.getCategory());
        }
        if(reqTask.getDeadline() != null) {
            newTask.setDeadline(reqTask.getDeadline());
        }
        if(reqTask.getImportance() != null) {
            newTask.setImportance(reqTask.getImportance());
        }
        if(reqTask.getCompleted() != null) {
            newTask.setCompleted(reqTask.getCompleted());
        }
        taskService.saveTask(newTask);
        return new ResponseEntity(newTask,HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addTask(@RequestBody Task reqTask) {
        Task newTask = new Task();
        newTask.setName(reqTask.getName());
        newTask.setCategory(reqTask.getCategory());
        newTask.setImportance(reqTask.getImportance());
        newTask.setDeadline(reqTask.getDeadline());
        newTask.setCompleted(false);
        taskService.saveTask(newTask);
        return new ResponseEntity(newTask, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity deleteTaskById(@PathVariable(value = "taskId") Long taskId){
        Optional<Task> fetchedTask;
        fetchedTask = taskService.getTaskById(taskId);
        if(!fetchedTask.isPresent()){
            return new ResponseEntity(new Message("Cannot delete this task because it does not exist."),HttpStatus.NOT_FOUND);
        }
        taskService.deleteTaskById(taskId);
        return new ResponseEntity(fetchedTask,HttpStatus.ACCEPTED);
    }
}

