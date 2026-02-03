package com.todo.todoApp.services;

import com.todo.todoApp.advice.ApiError;
import com.todo.todoApp.dto.UserSignUpDTO;
import com.todo.todoApp.dto.UserSignUpResponseDTO;
import com.todo.todoApp.entity.UserEntity;
import com.todo.todoApp.exceptions.ResourceNotFoundException;
import com.todo.todoApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    public UserEntity getUser(){
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        assert authenticatedUser != null;
        UserEntity userEntity = (UserEntity) authenticatedUser.getPrincipal();
        return userRepository.findById(userEntity.getId()).orElseThrow();
    }
}
