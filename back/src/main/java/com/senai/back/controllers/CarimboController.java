package com.senai.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.back.dtos.carimbos.CarimboInput;
import com.senai.back.dtos.carimbos.CarimboOutput;
import com.senai.back.services.CarimboService;


@RestController
@RequestMapping("/carimbos")
public class CarimboController {
    @Autowired
    private CarimboService service;

    @PostMapping("/{id}/usuarios")
    public CarimboOutput carimbar(@PathVariable Long id, @RequestBody CarimboInput input) {
        CarimboOutput carimbo = service.Carimbar(id, input);
        
        return carimbo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarimboOutput> read(@PathVariable Long id){
        CarimboOutput carimbo = service.read(id);
        if(carimbo == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(carimbo);

    }
}
