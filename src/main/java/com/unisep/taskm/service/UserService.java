package com.unisep.taskm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unisep.taskm.dto.UserDTO;
import com.unisep.taskm.form.UserForm;
import com.unisep.taskm.models.User;
import com.unisep.taskm.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public Page<UserDTO> getAllUsers(Pageable pagination) {
        Page<User> users = userRepository.findAll(pagination);
        Page<UserDTO> usersDTO = UserDTO.mapper(users);
        return usersDTO;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(UserForm user) {
        return userRepository.save(user.mapper());
    }

    public User updateUser(Long id, UserForm user) {
        return userRepository.save(user.mapper(id, userRepository));
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
