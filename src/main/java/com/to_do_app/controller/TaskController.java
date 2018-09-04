package com.to_do_app.controller;


import com.to_do_app.model.Task;
import com.to_do_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping(path="/api/task")   // the base url for tasks
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Task getTaskById(@PathVariable(value = "id") Integer id) {
        return taskService.getTaskById(id); //if statement to return message if the id doesn't exist.
    }

    // Post, not get, so this one's different
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task); //if statement to return message if the id doesn't exist.
    }

    // Put, not get
    @PutMapping(path = "/{id}")
    public Task updateTask(@PathVariable(value = "id") Integer id, @RequestBody Task task) {
        task.setId(id);
        return taskService.addTask(task);
    }

    // Delete, not get
    @Transactional
    @DeleteMapping(path = "/delete/{id}")
    public void deleteTaskById(@PathVariable(value = "id") Integer id){
        taskService.deleteTaskById(id);
    }

//    How to search for different data types? input is string, so I have to switch deadline/importance to string equivalents
//    @GetMapping(path="/search/{search}")
//    public @ResponseBody Iterable<Task> getTasksBySearch(@PathVariable(value = "search") String search){
//        return taskService.getTasksBySearch(search, search, search, search);
//    }

}

//    teaching purposes: prints variable passed into the url
//    @GetMapping(path = "/{id}")
//    public String getResourceById(@PathVariable(value = "id") String id) {
//        return id;
//    }
//}
