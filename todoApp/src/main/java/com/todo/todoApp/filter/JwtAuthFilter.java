package com.todo.todoApp.filter;

import com.todo.todoApp.entity.UserEntity;
import com.todo.todoApp.repository.UserRepository;
import com.todo.todoApp.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/v1/users");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final Cookie[] cookies = request.getCookies();
        Cookie cookie = cookies[0];
        String accessToken = cookie.getValue();

         String userId = jwtService.getUserIdFromToken(accessToken);


         if(userId != null){
             UserEntity user = userRepository.findById(userId).orElseThrow();
             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }

         filterChain.doFilter(request, response);



    }
}
