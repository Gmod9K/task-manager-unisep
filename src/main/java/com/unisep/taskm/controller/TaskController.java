package com.unisep.taskm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisep.taskm.dto.TaskDTO;
import com.unisep.taskm.dto.UserDTO;
import com.unisep.taskm.form.TaskForm;
import com.unisep.taskm.form.UpdateStatusTaskForm;
import com.unisep.taskm.form.UpdateTaskForm;
import com.unisep.taskm.models.Task;
import com.unisep.taskm.service.TaskService;
import com.unisep.taskm.validator.ErrorFormDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Retornar tarefas apartir de uma paginação")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefas encontradas",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = TaskDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefas não encontradas.",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )
    })
    @GetMapping
    public ResponseEntity<Page<TaskDTO>> getAll(
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {
        return ResponseEntity.ok(taskService.getAllTasks(pagination));
    }

    @Operation(summary = "Retornar tarefa por id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa encontrado",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = TaskDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "O id informado é inválido. Id deve ser um número inteiro",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrado.",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Task>> getById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar nova tarefa")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa criada com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = TaskDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "201",
            description = "Tarefa criada com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Campos inválidos informados",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )        
    })
    @PostMapping
    @Transactional
    public ResponseEntity<TaskDTO> post(@RequestBody @Valid TaskForm taskForm) {
        Task task = taskService.createTask(taskForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskDTO(task));
    }

    @Operation(summary = "Atualizar tarefa existente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa atualizada com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Campos inválidos informados",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )        
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TaskDTO> put(@PathVariable Long id, @RequestBody @Valid UpdateTaskForm taskForm) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            Task updatedTask = taskService.updateTask(id, taskForm);
            return ResponseEntity.ok(new TaskDTO(updatedTask));
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar status de uma tarefa existente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Status atualizado com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = TaskDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Status inválido informado. Deve ser PENDENTE, EM_ANDAMENTO ou CONCLUIDO",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )        
    })
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<TaskDTO> updateStatus(@PathVariable Long id, 
        @RequestBody @Valid UpdateStatusTaskForm taskForm) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            Task updatedTask = taskService.updateTask(id, taskForm);
            return ResponseEntity.ok(new TaskDTO(updatedTask));
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Excluir tarefa por id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tarefa excluída com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Tarefa excluída com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "O id informado é inválido. Id deve ser um número inteiro",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = ErrorFormDTO.class
                )
            ) }
        )
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
