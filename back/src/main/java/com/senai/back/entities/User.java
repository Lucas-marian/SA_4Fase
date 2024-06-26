package com.senai.back.entities;

import java.util.List;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.senai.back.entities.enums.Categoria;
import com.senai.back.entities.enums.Genero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeCompleto;
    private String dataNascimento;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String cpf;
    @Column(unique = true)@Email
    private String email;
    @Size(min = 8)
    private String senha;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column(unique = true)
    private String numeroCoren;

    @OneToOne
    private Endereco endereco;
    @OneToMany
    private List<VacinasAplicadas> vacinas;
    @OneToMany(mappedBy = "user")
    private List<Carimbo> carimbo;

    private boolean isAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isAdmin){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }    
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;    
    }

    @Override
    public String getPassword() {
        return senha;    
    }

    @Override
    public String getUsername() {
        return email;    
    }
}
