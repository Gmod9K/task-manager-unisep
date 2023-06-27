package com.unisep.taskm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisep.taskm.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
