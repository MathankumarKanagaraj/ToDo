package com.example.ToDo.Controller;

import com.example.ToDo.Entity.Task;
import com.example.ToDo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/add-task")
    public Task addTask(@RequestBody final Task task) {
        return this.taskService.saveTask(task);
    }

    @GetMapping("/retrieve-test")
    public List<Task> retrieveTest() {
        return this.taskService.retrieveTest();
    }

    @PutMapping("/update-task/{id}")
    public Task updatetask(@PathVariable final String id, @RequestBody final Task taskDetails) {
        return this.taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable final String id) {
        return this.taskService.deleteTask(id);

    }

    @GetMapping("/retrieve-tasks")
    public Page<Task> retrieveTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return this.taskService.retrieveTasks(PageRequest.of(page, size));
    }
}

