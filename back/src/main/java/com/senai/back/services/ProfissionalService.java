package com.senai.back.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.senai.back.dtos.users.profissional.ProfissionalInput;
import com.senai.back.dtos.users.profissional.ProfissionalOutput;
import com.senai.back.dtos.users.profissional.UpdateProfissionalDTO;
import com.senai.back.entities.User;
import com.senai.back.entities.enums.Categoria;
import com.senai.back.repositories.UserRepository;

@Service
public class ProfissionalService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    
    @Transactional
    public ProfissionalOutput create(ProfissionalInput input){
        User user = convertInputToUser(input);
        user = repository.save(user);

        return convertUserToOutput(user);

    }

    public List<ProfissionalOutput> list(){
        return repository
            .findAll()
            .stream()
            .map(u -> convertUserToOutput(u))
            .toList();

    }

    public ProfissionalOutput read(Long id){
        User user = repository.findById(id).orElse(null);

        return convertUserToOutput(user);

    }

    @Transactional
    public ProfissionalOutput update(Long id, UpdateProfissionalDTO input){
        if(repository.existsById(id)){
            User user = convertInputToUpdateProfissional(input);
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

//////// conversão

    private User convertInputToUpdateProfissional(UpdateProfissionalDTO input){
        User user = new User();
            user.setNomeCompleto(input.getNomeCompleto());
            var senhaCryptografada = new BCryptPasswordEncoder().encode(input.getSenha());
            user.setSenha(senhaCryptografada);
            user.setAdmin(true);
            user.setCategoria(Categoria.ADULTO);
        return user;

    }

    private User convertInputToUser(ProfissionalInput input){
        User user = new User();
            user.setNomeCompleto(input.getNomeCompleto());
            user.setDataNascimento(input.getDataNascimento());
            user.setGenero(input.getGenero());
            user.setCpf(input.getCpf());
            user.setEmail(input.getEmail());
            var senhaCryptografada = new BCryptPasswordEncoder().encode(input.getSenha());
            user.setSenha(senhaCryptografada);
            user.setCategoria(Categoria.ADULTO);
            user.setNumeroCoren(input.getNumeroCoren());
            user.setAdmin(true);
        return user;

    }

    private ProfissionalOutput convertUserToOutput(User user){
        if(user == null){
            return null;
        }

        ProfissionalOutput output = new ProfissionalOutput(
            user.getId(),
            user.getNomeCompleto(),
            user.getDataNascimento(),
            user.getGenero(),
            user.getEmail(),
            user.getNumeroCoren()
            
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
