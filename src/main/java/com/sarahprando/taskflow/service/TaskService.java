package com.sarahprando.taskflow.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sarahprando.taskflow.entity.Task;
import com.sarahprando.taskflow.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task get(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public List<Task> list() {
        Sort sort = Sort.by("deadline").descending().and(Sort.by("title").ascending());
        return taskRepository.findAll(sort);
    }

    public Task update(Task task) {
        taskRepository.save(task);
        return task;
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        taskRepository.deleteById(id);
    }
}
