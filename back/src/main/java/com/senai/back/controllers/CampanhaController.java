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

import com.senai.back.dtos.campanhas.CampanhaInput;
import com.senai.back.dtos.campanhas.CampanhaOutput;
import com.senai.back.services.CampanhaService;



@RestController
@RequestMapping("/campanhas")
public class CampanhaController {
    @Autowired
    private CampanhaService service;
    
    @GetMapping
    public ResponseEntity<List<CampanhaOutput>> list(){
        List<CampanhaOutput> list = service.list();

        return ResponseEntity.ok(list);

    }

    @PostMapping
    public ResponseEntity<CampanhaOutput> create(@RequestBody CampanhaInput campanha){
        CampanhaOutput output = service.create(campanha);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaOutput> read(@PathVariable Long id){
        CampanhaOutput campanha = service.read(id);

        if(campanha == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(campanha);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanhaOutput> update(@PathVariable Long id, @RequestBody CampanhaInput input){
        CampanhaOutput output = service.update(id, input);

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

