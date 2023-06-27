package com.unisep.taskm.form;

import com.unisep.taskm.models.User;
import com.unisep.taskm.repository.UserRepository;

// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserForm {
    
    @Size(max = 50)
    private String nome;

    @NotNull
    @NotEmpty
    // @Email
    private String email;

    @NotNull
    @NotEmpty
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public User mapper(Long id, UserRepository userRepository) {
        User user = userRepository.findById(id).get();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        return user;
    }

    public User mapper() {
        return new User(nome, email, senha);
    }
    
}
