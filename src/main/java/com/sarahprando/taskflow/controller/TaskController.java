package com.sarahprando.taskflow.controller;

import com.sarahprando.taskflow.entity.Task;
import com.sarahprando.taskflow.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    List<Task> create(@RequestBody @Valid Task task) {
        return taskService.create(task);
    }

    @GetMapping
    List<Task> list() {
        return taskService.list();
    }

    @PutMapping
    List<Task> update(@RequestBody @Valid Task task) {
        return taskService.update(task);

    }

    @DeleteMapping("/{id}")
    List<Task> delete(@PathVariable("id") Long id) {
        return taskService.delete(id);
    }
}
