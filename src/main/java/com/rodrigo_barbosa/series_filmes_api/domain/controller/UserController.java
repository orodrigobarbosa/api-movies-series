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

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    // Método para cadastrar um novo usuário
    @PostMapping("/cadastrar")
    public User cadastrar(@RequestBody User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha())); // Codifica a senha do usuário
        return userService.cadastrarUser(user); // Chama o serviço para cadastrar o usuário
    }


    // Método para listar todos os usuários
    @GetMapping
    public List<User> listarTodos() {
        return userService.listarUsers();
    }

    // Método para buscar um usuário por ID
    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Integer id) {
        return userService.buscarUserPorId(id);
    }

    // Método para deletar um usuário por ID
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Integer id) {
        userService.deletarUser(id);
    }

    // Método para atualizar um usuário por ID
    @PutMapping("/atualizar/{id}")
    public User atualizarPorId(@PathVariable Integer id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("Id incorreto"); // Verifica se o ID no path é igual ao ID no corpo da requisição
        }
        return userService.atualizarUser(id, user); // Chama o serviço para atualizar o usuário
    }

    // Método para efetuar login **************


    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody LoginRequestDto loginRequest) throws Exception {
        try {
            System.out.println("Tentando autenticar CPF: " + loginRequest.getCpf());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getCpf(), loginRequest.getSenha())
            );
            System.out.println("Autenticação bem-sucedida para CPF: " + loginRequest.getCpf());
        } catch (AuthenticationException e) {
            System.err.println("Falha na autenticação para CPF: " + loginRequest.getCpf());
            throw new Exception("CPF ou senha incorretos", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getCpf());
        return jwtUtil.generateToken(userDetails);
    }
}
