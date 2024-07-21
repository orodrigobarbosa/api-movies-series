package com.rodrigo_barbosa.series_filmes_api.domain.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    // Define um bean para o PasswordEncoder usando BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define um bean para o AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Define a configuração de segurança para a aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configura as regras de autorização e outros aspectos de segurança
        http.csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Permite acesso público aos endpoints do Swagger
                        .requestMatchers("/login", "/register").permitAll() // Permite acesso público aos endpoints de login e registro
                        .requestMatchers("/user").permitAll() // Permite acesso público ao endpoint de listagem de usuários
                        .requestMatchers("/user/cadastrar").permitAll() // Permite acesso público ao endpoint de cadastro de usuário
                        .requestMatchers("/user/deletar/**").permitAll() // Permite acesso público ao endpoint de deletar usuário
                        .anyRequest().authenticated() // Requer autenticação para outros endpoints
                )
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint) // Configura o ponto de entrada para erros de autenticação
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configura a política de criação de sessão para stateless

        // Adiciona o filtro JWT antes do filtro de autenticação padrão
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Define o customizador de segurança para ignorar certos padrões de URL
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"); // Ignora URLs específicas para Swagger
    }
}
