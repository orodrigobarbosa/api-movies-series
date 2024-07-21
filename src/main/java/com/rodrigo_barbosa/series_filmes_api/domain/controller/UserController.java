package com.rodrigo_barbosa.series_filmes_api.domain.controller;


import com.rodrigo_barbosa.series_filmes_api.domain.dto.LoginRequestDto;
import com.rodrigo_barbosa.series_filmes_api.domain.jwt.JwtUtil;
import com.rodrigo_barbosa.series_filmes_api.domain.model.User;
import com.rodrigo_barbosa.series_filmes_api.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private  AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public User cadastrar(@RequestBody User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userService.cadastrarUser(user);
    }

    @GetMapping
    public List<User> listarTodos() {
        return userService.listarUsers();
    }

    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Integer id) {
        return userService.buscarUserPorId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Integer id) {
        userService.deletarUser(id);
    }

    @PutMapping("/atualizar/{id}")
    public User atualizarPorId(@PathVariable Integer id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("Id incorreto");
        }
        return userService.atualizarUser(id, user);
    }

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody LoginRequestDto loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getCpf(), loginRequest.getSenha())
            );
        } catch (AuthenticationException e) {
            throw new Exception("CPF ou senha incorretos", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getCpf());
        return jwtUtil.generateToken(userDetails);
    }
}
