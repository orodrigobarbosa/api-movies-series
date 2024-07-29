package com.rodrigo_barbosa.series_filmes_api.domain.service;

import com.rodrigo_barbosa.series_filmes_api.domain.model.User;
import com.rodrigo_barbosa.series_filmes_api.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public User cadastrarUser(User user) {
        return userRepository.save(user);
    }

    public List<User> listarUsers() {
        return userRepository.findAll();
    }

    public User buscarUserPorId(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public void deletarUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User atualizarUser(Integer id, User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("Usuário sem Id");
        }
        User usuarioExistente = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        modelMapper.map(user, usuarioExistente);
        return userRepository.save(usuarioExistente);
    }


    //login cpf +  senha

    //
    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        User user = userRepository.findByCpf(cpf);
        if (user == null) {
            throw new UsernameNotFoundException("CPF não encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getCpf(), user.getSenha(), new ArrayList<>());
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
