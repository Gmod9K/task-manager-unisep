package com.unisep.taskm.dto;

import org.springframework.data.domain.Page;

import com.unisep.taskm.models.User;

public class UserDTO {

    private Long id;

    private String nome;

    private String email;

    public UserDTO(User user) {
        super();
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static Page<UserDTO> mapper(Page<User> users) {
        return users.map(UserDTO::new);
    }
    
}
