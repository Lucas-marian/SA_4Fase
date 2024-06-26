package com.senai.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.back.dtos.campanhas.CampanhaInput;
import com.senai.back.dtos.campanhas.CampanhaOutput;
import com.senai.back.entities.Campanha;
import com.senai.back.repositories.CampanhaRepository;

@Service
public class CampanhaService {
    @Autowired
    private CampanhaRepository repository;

    @Transactional
    public CampanhaOutput create(CampanhaInput input){
        Campanha campanha = convertInputToCampanha(input);
        campanha = repository.save(campanha);

        return convertCampanhaToOutput(campanha);

    }

    public List<CampanhaOutput> list(){
        return repository
            .findAll()
            .stream()
            .map(u -> convertCampanhaToOutput(u))
            .toList();

    }

    public CampanhaOutput read(Long id){
        Campanha campanha = repository.findById(id).orElse(null);

        return convertCampanhaToOutput(campanha);

    }

    @Transactional
    public CampanhaOutput update(Long id, CampanhaInput input){
        if(repository.existsById(id)){
            Campanha campanha = convertInputToCampanha(input);
                campanha.setId(id);
            campanha = repository.save(campanha);
            return convertCampanhaToOutput(campanha);

        }else {
            return null;

        }

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

//////// conversão
    private Campanha convertInputToCampanha(CampanhaInput input){
        Campanha campanha = new Campanha();
            campanha.setNomeCampanha(input.getNomeCampanha());
            campanha.setDescricao(input.getDescricao());
            campanha.setDataInicio(input.getDataInicio());
            campanha.setDataTermino(input.getDataTermino());
        return campanha;

    }

    private CampanhaOutput convertCampanhaToOutput(Campanha campanha){
        if(campanha == null){
            return null;
        }

        CampanhaOutput output = new CampanhaOutput(
            campanha.getId(),
            campanha.getNomeCampanha(),
            campanha.getDescricao(),
            campanha.getDataInicio(),
            campanha.getDataTermino()

        );
        return output;

    }
////////
}