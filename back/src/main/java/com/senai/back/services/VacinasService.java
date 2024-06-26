package com.senai.back.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.back.dtos.vacinas.VacinasInput;
import com.senai.back.dtos.vacinas.VacinasOutput;
import com.senai.back.entities.Vacinas;
import com.senai.back.repositories.VacinasRetository;

import jakarta.transaction.Transactional;

@Service
public class VacinasService {
    @Autowired
    private VacinasRetository repository;

    @Transactional
    public VacinasOutput create(VacinasInput input){
        Vacinas vacinas = convertInputToVacina(input);
        vacinas = repository.save(vacinas);

        return convertVacinaToOutput(vacinas);

    }

    public List<VacinasOutput> list(){
        return repository
            .findAll()
            .stream()
            .map(u -> convertVacinaToOutput(u))
            .toList();

    }

    public VacinasOutput read(Long id){
        Vacinas vacinas = repository.findById(id).orElse(null);

        return convertVacinaToOutput(vacinas);

    }

    @Transactional
    public VacinasOutput update(Long id, VacinasInput input){
        if(repository.existsById(id)){
            Vacinas vacinas = convertInputToVacina(input);
                vacinas.setId(id);
            vacinas = repository.save(vacinas);
            return convertVacinaToOutput(vacinas);

        }else {
            return null;

        }

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

//////// conversão
    private Vacinas convertInputToVacina(VacinasInput input){
        Vacinas vacinas = new Vacinas();
            vacinas.setNomeVacina(input.getNomeVacina());
            vacinas.setLote(input.getLote());
        return vacinas;

    }

    private VacinasOutput convertVacinaToOutput(Vacinas vacinas){
        if(vacinas == null){
            return null;
        }

        VacinasOutput output = new VacinasOutput(
            vacinas.getId(),
            vacinas.getNomeVacina(),
            vacinas.getLote()
            
        );
        return output;

    }
////////
}
