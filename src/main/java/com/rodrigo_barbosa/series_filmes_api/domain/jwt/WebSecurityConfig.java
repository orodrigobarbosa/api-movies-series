package com.rodrigo_barbosa.series_filmes_api.domain.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //o Lombok gera um construtor que garante que todas as dependências final sejam corretamente injetadas pelo Spring
public class WebSecurityConfig {

    // Injeção das dependências necessárias para autenticação JWT e gerenciamento de erros
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    // Bean para configurar o codificador de senha usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para configurar o AuthenticationManager utilizado para autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuração da cadeia de filtros de segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desabilita a proteção CSRF, geralmente usado para APIs
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Permite acesso aos endpoints do Swagger sem autenticação
                        .requestMatchers("user/login", "/user/cadastrar").permitAll() // Permite acesso aos endpoints de login e cadastro sem autenticação
                        .requestMatchers("/user").permitAll() // Permite acesso ao endpoint de listagem de usuários sem autenticação
                        .requestMatchers("/user/{id}").permitAll() // Permite acesso ao endpoint de busca de usuário por ID sem autenticação
                        .anyRequest().authenticated() // Exige autenticação para todos os outros endpoints
                )
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint) // Define o Entry Point para tratamento de exceções de autenticação
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Define a política de gerenciamento de sessão como STATELESS (sem estado)

        // Adiciona o filtro JWT antes do filtro padrão de autenticação de usuário e senha
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Constrói e retorna a cadeia de filtros de segurança
    }
}
