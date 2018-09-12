package com.to_do_app.controller;


import com.to_do_app.model.Task;
import com.to_do_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

//    @PutMapping(path = "/{taskId}")
//    public Task updateTask(@PathVariable(value = "taskId") Long taskId, @RequestBody Task task) {
//        task.setTaskId(taskId);
//        return taskService.saveTask(task);

    @GetMapping(path = "/{taskId}")
    @ResponseBody
    public Task getTaskById(@PathVariable(value = "taskId") Long taskId) {

        return taskService.getTaskById(taskId).get(); //if statement to return message if the taskId doesn't exist.

//        return taskService.getTaskById(taskId).get(); //if statement to return message if the taskId doesn't exist.
    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task); //if statement to return message if the taskId doesn't exist.
    }

    @PutMapping(path = "/{taskId")
    public Task updateTask(@PathVariable(value = "taskId") Long taskId, @RequestBody Task task){
        // JJJ Jeroen The API is not getting to this point
        System.out.println("I'm in put in the controller");
        Optional<Task> savedTask = taskService.getTaskById(taskId);
        if (savedTask.isPresent()) {
            System.out.println("JJJ : Task found");
        } else {
            System.out.println("JJJ : Task not found");
        }

        task.setTaskId(taskId);
        return taskService.saveTask(task);
    }


//    @Transactional
    @DeleteMapping(path = "/{taskId}")
    public void deleteTaskById(@PathVariable(value = "taskId") Long taskId){
        taskService.deleteTaskById(taskId);
    }

//    How to search for different data types? input is string, so I have to switch deadline/importance to string equivalents
//    @GetMapping(path="/search/{search}")
//    public @ResponseBody Iterable<Task> getTasksBySearch(@PathVariable(value = "search") String search){
//        return taskService.getTasksBySearch(search, search, search, search);
//    }

}

