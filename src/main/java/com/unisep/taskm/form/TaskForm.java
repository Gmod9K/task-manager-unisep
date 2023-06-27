package com.unisep.taskm.form;

import com.unisep.taskm.enumeration.TaskStatus;
import com.unisep.taskm.models.Task;
import com.unisep.taskm.repository.TaskRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskForm {
    
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String titulo;

    @NotEmpty
    @Size(max = 255)
    private String descricao;

    @NotEmpty
    private String status;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task mapper(Long id, TaskRepository taskRepository) {
        Task task = taskRepository.findById(id).get();
        task.setTitulo(titulo);
        task.setDescricao(descricao);
        task.setStatus(TaskStatus.PENDENTE);
        return task;
    }

    public Task mapper() {
        return new Task(titulo, descricao, status);
    }

}
