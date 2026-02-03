package com.todo.todoApp.services;

import com.todo.todoApp.dto.UserLoginDTO;
import com.todo.todoApp.dto.UserSignUpDTO;
import com.todo.todoApp.dto.UserSignUpResponseDTO;
import com.todo.todoApp.entity.UserEntity;
import com.todo.todoApp.exceptions.ResourceNotFoundException;
import com.todo.todoApp.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;


    public UserSignUpResponseDTO registerUser(UserSignUpDTO userSignUpDTO) throws Exception {
        if(userSignUpDTO == null) throw new ResourceNotFoundException("Each field is required");
        UserEntity userEntity = modelMapper.map(userSignUpDTO, UserEntity.class);
        Optional<UserEntity> existedUser = userRepository.findByEmail(userSignUpDTO.getEmail());
        if (existedUser.isPresent()) throw new Exception("User already exist");
        userEntity.setPassword(passwordEncoder.encode(userSignUpDTO.getPassword()));
        UserEntity user = userRepository.save(userEntity);
        return modelMapper.map(user, UserSignUpResponseDTO.class);
    }


    public UserSignUpResponseDTO loginUser(UserLoginDTO userLoginDTO, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        assert user != null;
        String token = jwtService.generateAccessToken(user);
        Cookie cookie = new Cookie("AccessToken", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return modelMapper.map(user, UserSignUpResponseDTO.class);

    }

}
