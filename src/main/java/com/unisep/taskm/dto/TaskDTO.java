package com.unisep.taskm.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.unisep.taskm.enumeration.TaskStatus;
import com.unisep.taskm.models.Task;

public class TaskDTO {
    
    private Long id;

    private String titulo;
    
    private String descricao;

    private LocalDateTime dataCriacao;

    private TaskStatus status;

    public TaskDTO(Task task) {
        super();
        this.id = task.getId();
        this.titulo = task.getTitulo();
        this.descricao = task.getDescricao();
        this.dataCriacao = task.getDataCriacao();
        this.status = task.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public static Page<TaskDTO> mapper(Page<Task> tasks) {
        return tasks.map(TaskDTO::new);
    }
    
}
