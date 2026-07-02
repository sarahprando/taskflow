package com.sarahprando.taskflow.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sarahprando.taskflow.entity.Task;
import com.sarahprando.taskflow.repository.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> create(Task task) {
        taskRepository.save(task);
        return list();
    }

    public List<Task> list() {
        Sort sort = Sort.by("deadline").descending().and(Sort.by("title").ascending());
        return taskRepository.findAll(sort);
    }

    public List<Task> update(Task task) {
        taskRepository.save(task);
        return list();

    }

    public List<Task> delete(Long id) {
        taskRepository.deleteById(id);
        return list();
    }
}
