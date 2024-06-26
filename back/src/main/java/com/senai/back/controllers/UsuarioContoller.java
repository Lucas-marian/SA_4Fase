package com.senai.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.back.dtos.enderecos.EnderecoInput;
import com.senai.back.dtos.enderecos.EnderecoOutput;
import com.senai.back.dtos.users.usuario.ResetSenhaDTO;
import com.senai.back.dtos.users.usuario.UpdateUserDTO;
import com.senai.back.dtos.users.usuario.UsuarioInput;
import com.senai.back.dtos.users.usuario.UsuarioOutput;
import com.senai.back.entities.User;
import com.senai.back.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioContoller {
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity<List<UsuarioOutput>> list(Pageable page, User example) {
        List<UsuarioOutput> list = service.list(page, example);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<UsuarioOutput> create(@Valid @RequestBody UsuarioInput users){
        UsuarioOutput output = service.create(users);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioOutput> read(@RequestBody String email){
        UsuarioOutput users = service.read(email);

        if(users == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(users);

    }

    @PutMapping("/{id}/atualizarDados")
    public ResponseEntity<UsuarioOutput> update(@PathVariable Long id, @RequestBody UpdateUserDTO input){
        UsuarioOutput output = service.update(id, input);

        if(output == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(output);

    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}/endereco")
    public EnderecoOutput updateEndereco(@PathVariable Long id, @RequestBody EnderecoInput input) {
        EnderecoOutput endereco = service.updateEndereco(id, input);
        
        return endereco;
    }

    @PutMapping("/senha")
    public ResponseEntity<UsuarioOutput> updateSenha(@RequestBody ResetSenhaDTO input){
        UsuarioOutput output = service.updateSenha(input);

        return ResponseEntity.ok(output);
    }
}
