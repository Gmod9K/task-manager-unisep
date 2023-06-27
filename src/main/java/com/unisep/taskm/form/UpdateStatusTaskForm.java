package com.unisep.taskm.form;

import com.unisep.taskm.enumeration.TaskStatus;
import com.unisep.taskm.models.Task;
import com.unisep.taskm.repository.TaskRepository;

import jakarta.validation.constraints.NotEmpty;

public class UpdateStatusTaskForm {
    
    @NotEmpty
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task mapper(Long id, TaskRepository taskRepository) {
        Task task = taskRepository.findById(id).get();
        task.setStatus(TaskStatus.valueOf(status));
        return task;
    }

    public Task mapper() {
        return new Task(status);
    }
}
