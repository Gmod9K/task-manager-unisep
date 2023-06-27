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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisep.taskm.dto.UserDTO;
import com.unisep.taskm.form.UserForm;
import com.unisep.taskm.models.User;
import com.unisep.taskm.service.UserService;
import com.unisep.taskm.validator.ErrorFormDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Retornar usuários apartir de uma paginação")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuários encontrados",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuários não encontrados.",
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
    public ResponseEntity<Page<UserDTO>> getAll(
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pagination) {
        return ResponseEntity.ok(userService.getAllUsers(pagination));
    }

    @Operation(summary = "Retornar usuário por id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado",
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
            description = "Usuário não encontrado.",
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
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(
        @Parameter(description = "Id do usuário que deseja pesquisar, deve ser um número")
        @PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar novo usuário")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário criado com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso",
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
    public ResponseEntity<UserDTO> post(@RequestBody @Valid UserForm userForm) {
        User user = userService.createUser(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
    }

    @Operation(summary = "Atualizar usuário existente")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário atualizado com sucesso",
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
            description = "Usuário não encontrado",
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
    public ResponseEntity<UserDTO> put(
        @Parameter(description = "Id do usuário a ser atualizado.")
        @PathVariable Long id, @RequestBody @Valid UserForm userForm) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            User updatedUser = userService.updateUser(id, userForm);
            return ResponseEntity.ok(new UserDTO(updatedUser));
        }
        
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Excluir usuário por id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuário excluído com sucesso",
            content = { @Content(
                mediaType = "application/json",
                schema = @Schema(
                    implementation = UserDTO.class
                )
            ) }
        ),
        @ApiResponse(
            responseCode = "204",
            description = "Usuário excluído com sucesso",
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
            description = "Usuário não encontrado",
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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
