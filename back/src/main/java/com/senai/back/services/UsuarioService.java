package com.senai.back.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.senai.back.dtos.enderecos.EnderecoInput;
import com.senai.back.dtos.enderecos.EnderecoOutput;
import com.senai.back.dtos.users.usuario.ResetSenhaDTO;
import com.senai.back.dtos.users.usuario.UpdateUserDTO;
import com.senai.back.dtos.users.usuario.UsuarioInput;
import com.senai.back.dtos.users.usuario.UsuarioOutput;
import com.senai.back.entities.Endereco;
import com.senai.back.entities.User;
import com.senai.back.repositories.UserRepository;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private EnderecoService enderecoService;
    
    @Transactional
    public UsuarioOutput create(UsuarioInput input){
        User user = convertInputToUser(input);
        user = repository.save(user);

        return convertUserToOutput(user);

    }

    public List<UsuarioOutput> list(Pageable page, User userExample) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAny()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

        Example<User> example = Example.of(userExample, matcher);

        return repository
                .findAll(example, page)
                .stream()
                .map(u -> convertUserToOutput(u))
                .toList();
    }

    public UsuarioOutput read(String email){
        User user = repository.findByEmail(email);

        return convertUserToOutput(user);

    }

    @Transactional
    public UsuarioOutput update(Long id, UpdateUserDTO input){
        if(repository.existsById(id)){
            User user = convertInputToUpdateUser(input);
                user.setId(id);
            user = repository.save(user);
            return convertUserToOutput(user);

        }else {
            return null;

        }

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

    public EnderecoOutput updateEndereco(Long id, EnderecoInput input){
        if(repository.existsById(id)){
            User user = repository.findById(id).get();
            Endereco endereco = enderecoService.convertInputToEndereco(input);
            user.setEndereco(endereco);
            repository.save(user);

            return enderecoService.convertEnderecoToOutput(endereco);
        }

        return null;
    }

    public UsuarioOutput updateSenha(ResetSenhaDTO input){
        User user = repository.findByEmail(input.getEmailUser());

        var senhaCryptografada = new BCryptPasswordEncoder().encode(input.getNewPassword());
        user.setSenha(senhaCryptografada);
        user = repository.save(user);

        return convertUserToOutput(user);
    }

//////// conversão
    private User convertInputToUpdateUser(UpdateUserDTO input){
        User user = new User();
            user.setNomeCompleto(input.getNomeCompleto());
            var senhaCryptografada = new BCryptPasswordEncoder().encode(input.getSenha());
            user.setSenha(senhaCryptografada);
            user.setCategoria(input.getCategoria());
            user.setAdmin(false);
        return user;

    }

    private User convertInputToUser(UsuarioInput input){
        User user = new User();
            user.setNomeCompleto(input.getNomeCompleto());
            user.setDataNascimento(input.getDataNascimento());
            user.setGenero(input.getGenero());
            user.setCpf(input.getCpf());
            user.setEmail(input.getEmail());
            var senhaCryptografada = new BCryptPasswordEncoder().encode(input.getSenha());
            user.setSenha(senhaCryptografada);
            user.setAdmin(false);
        return user;

    }

    private UsuarioOutput convertUserToOutput(User user){
        if(user == null){
            return null;
        }

        UsuarioOutput output = new UsuarioOutput(
            user.getId(),
            user.getNomeCompleto(),
            user.getDataNascimento(),
            user.getGenero(),
            user.getCpf(),
            user.getEmail(),
            user.getCategoria()
            
        );
        return output;

    }
////////

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        } 
        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
        
    }
}
