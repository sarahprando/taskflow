package com.sarahprando.taskflow.controller;

import com.sarahprando.taskflow.entity.Task;
import com.sarahprando.taskflow.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
    Task create(@RequestBody @Valid Task task) {
        return taskService.create(task);
    }

    @GetMapping("/{id}")
    Task get(@PathVariable("id") Long id) {
        return taskService.get(id);
    }

    @GetMapping
    List<Task> list() {
        return taskService.list();
    }

    @PutMapping
    Task update(@RequestBody @Valid Task task) {
        return taskService.update(task);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
