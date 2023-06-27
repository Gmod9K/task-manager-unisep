package com.unisep.taskm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unisep.taskm.dto.TaskDTO;
import com.unisep.taskm.form.TaskForm;
import com.unisep.taskm.form.UpdateStatusTaskForm;
import com.unisep.taskm.form.UpdateTaskForm;
import com.unisep.taskm.models.Task;
import com.unisep.taskm.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public Page<TaskDTO> getAllTasks(Pageable pagination) {
        Page<Task> tasks = taskRepository.findAll(pagination);
        Page<TaskDTO> tasksDTO = TaskDTO.mapper(tasks);
        return tasksDTO;
    }
    
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(TaskForm task) {
        return taskRepository.save(task.mapper());
    }

    public Task updateTask(Long id, UpdateTaskForm task) {
        return taskRepository.save(task.mapper(id, taskRepository));
    }

    public Task updateTask(Long id, UpdateStatusTaskForm task) {
        return taskRepository.save(task.mapper(id, taskRepository));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
