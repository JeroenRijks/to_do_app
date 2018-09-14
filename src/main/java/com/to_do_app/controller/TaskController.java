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
//
//    @PutMapping(path = "/{taskId}")
//    public Task updateTask(@PathVariable(value = "taskId") Long taskId, @RequestBody Task task) {
//        task.setId(taskId);
//        return taskService.addTask(task);

    @GetMapping(path = "/{taskId}")
    @ResponseBody
    public Task getTaskById(@PathVariable(value = "taskId") Long taskId) {

        Task a = taskService.getTaskById(taskId).get(); //if statement to return message if the taskId doesn't exist.

        return a;

//        return taskService.getTaskById(taskId).get(); //if statement to return message if the taskId doesn't exist.
    }
//    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task); //if statement to return message if the taskId doesn't exist.
    }

    @Transactional
    @DeleteMapping(path = "/delete/{taskId}")
    public void deleteTaskById(@PathVariable(value = "taskId") Long taskId){
        taskService.deleteTaskById(taskId);
    }

//    How to search for different data types? input is string, so I have to switch deadline/importance to string equivalents
//    @GetMapping(path="/search/{search}")
//    public @ResponseBody Iterable<Task> getTasksBySearch(@PathVariable(value = "search") String search){
//        return taskService.getTasksBySearch(search, search, search, search);
//    }

}

