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

import com.senai.back.dtos.unidadesSaude.UnidadeSaudeInput;
import com.senai.back.dtos.unidadesSaude.UnidadeSaudeOutput;
import com.senai.back.services.UnidadeSaudeService;


@RestController
@RequestMapping("/unidadesDeSaude")
public class UnidadeSaudeController {
    @Autowired
    private UnidadeSaudeService service;
    
    @GetMapping
    public ResponseEntity<List<UnidadeSaudeOutput>> list(){
        List<UnidadeSaudeOutput> list = service.list();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<UnidadeSaudeOutput> create(@RequestBody UnidadeSaudeInput unidadeSaude){
        UnidadeSaudeOutput output = service.create(unidadeSaude);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeSaudeOutput> read(@PathVariable Long id){
        UnidadeSaudeOutput unidadeSaude = service.read(id);

        if(unidadeSaude == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(unidadeSaude);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeSaudeOutput> update(@PathVariable Long id, @RequestBody UnidadeSaudeInput input){
        UnidadeSaudeOutput output = service.update(id, input);

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
