package com.rodrigo_barbosa.series_filmes_api.domain.controller;

import com.rodrigo_barbosa.series_filmes_api.domain.jwt.JwtUtil;
import com.rodrigo_barbosa.series_filmes_api.domain.model.User;
import com.rodrigo_barbosa.series_filmes_api.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getCpf(), user.getSenha())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect CPF or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getCpf());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userService.save(user);
    }
}
