package com.senai.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.senai.back.services.EnderecoService;



@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    
    @GetMapping
    public ResponseEntity<List<EnderecoOutput>> list(){
        List<EnderecoOutput> list = service.list();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<EnderecoOutput> create(@RequestBody EnderecoInput endereco){
        EnderecoOutput output = service.create(endereco);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoOutput> read(@PathVariable Long id){
        EnderecoOutput endereco = service.read(id);

        if(endereco == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(endereco);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoOutput> update(@PathVariable Long id, @RequestBody EnderecoInput input){
        EnderecoOutput output = service.update(id, input);

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
    
}
