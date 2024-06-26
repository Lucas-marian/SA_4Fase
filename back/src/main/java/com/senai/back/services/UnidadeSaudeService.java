package com.senai.back.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.back.dtos.unidadesSaude.UnidadeSaudeInput;
import com.senai.back.dtos.unidadesSaude.UnidadeSaudeOutput;
import com.senai.back.entities.UnidadeSaude;
import com.senai.back.repositories.UnidadeSaudeRepository;

@Service
public class UnidadeSaudeService {
    @Autowired
    private UnidadeSaudeRepository repository;

    @Transactional
    public UnidadeSaudeOutput create(UnidadeSaudeInput input){
        UnidadeSaude unidadeSaude = convertInputToUnidade(input);
        unidadeSaude = repository.save(unidadeSaude);

        return convertUnidadeToOutput(unidadeSaude);

    }

    public List<UnidadeSaudeOutput> list(){
        return repository
            .findAll()
            .stream()
            .map(u -> convertUnidadeToOutput(u))
            .toList();

    }

    public UnidadeSaudeOutput read(Long id){
        UnidadeSaude unidadeSaude = repository.findById(id).orElse(null);

        return convertUnidadeToOutput(unidadeSaude);

    }

    @Transactional
    public UnidadeSaudeOutput update(Long id, UnidadeSaudeInput input){
        if(repository.existsById(id)){
            UnidadeSaude unidadeSaude = convertInputToUnidade(input);
                unidadeSaude.setId(id);
            unidadeSaude = repository.save(unidadeSaude);
            return convertUnidadeToOutput(unidadeSaude);

        }else {
            return null;

        }

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

//////// conversão
    private UnidadeSaude convertInputToUnidade(UnidadeSaudeInput input){
        UnidadeSaude unidadeSaude = new UnidadeSaude();
            unidadeSaude.setNomeUnidade(input.getNomeUnidade());
            unidadeSaude.setPhone(input.getPhone());
            unidadeSaude.setWhatsapp(input.getWhatsapp());
            unidadeSaude.setNomeRua(input.getNomeRua());
            unidadeSaude.setNumeroUnidade(input.getNumeroUnidade());
            unidadeSaude.setCep(input.getCep());
            unidadeSaude.setBairro(input.getBairro());
            unidadeSaude.setCidade(input.getCidade());
            unidadeSaude.setEstado(input.getEstado());
        return unidadeSaude;

    }

    private UnidadeSaudeOutput convertUnidadeToOutput(UnidadeSaude unidadeSaude){
        if(unidadeSaude == null){
            return null;
        }

        UnidadeSaudeOutput output = new UnidadeSaudeOutput(
            unidadeSaude.getId(),
            unidadeSaude.getNomeUnidade(),
            unidadeSaude.getPhone(),
            unidadeSaude.getWhatsapp(),
            unidadeSaude.getNomeRua(),
            unidadeSaude.getNumeroUnidade(),
            unidadeSaude.getCep(),
            unidadeSaude.getBairro(),
            unidadeSaude.getCidade(),
            unidadeSaude.getEstado()
        );
        return output;

    }
////////
}
