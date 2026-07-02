package com.sarahprando.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarahprando.taskflow.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
