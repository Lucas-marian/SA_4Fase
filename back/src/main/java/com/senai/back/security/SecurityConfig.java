package com.senai.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import com.senai.back.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CustomCorsFilter customCorsFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(
        AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder builder = http.getSharedObject(
            AuthenticationManagerBuilder.class
        );
        builder.userDetailsService(usuarioService).passwordEncoder(getPasswordEncoder());
        var authentication = builder.build();

        http.authorizeHttpRequests(request ->
            request
                .requestMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios")
                .permitAll()
                .requestMatchers(HttpMethod.PUT, "/usuarios")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/profissionais")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/usuarios")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuarios")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/profissionais")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/profissionais")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/profissionais")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/enderecos")
                .hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/enderecos")
                .hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/enderecos")
                .hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/enderecos")
                .hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/vacinas")
                .hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/vacinas")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/vacinas")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/vacinas")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/aplicacoes")
                .hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/aplicacoes")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/aplicacoes")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/aplicacoes")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/unidadesDeSaude")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/unidadesDeSaude")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/unidadesDeSaude")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/unidadesDeSaude")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/carimbos")
                .permitAll()
                .requestMatchers(HttpMethod.PUT, "/carimbos")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/campanhas")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/campanhas")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/campanhas")
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/campanhas")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
               
            )
            .addFilterBefore(customCorsFilter, SessionManagementFilter.class)   
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable()) 
            .authenticationManager(authentication)
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
