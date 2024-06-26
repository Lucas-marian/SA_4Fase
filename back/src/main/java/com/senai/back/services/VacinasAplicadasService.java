package com.senai.back.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.back.dtos.vacinasAplicadas.VacinasAplicadasInput;
import com.senai.back.dtos.vacinasAplicadas.VacinasAplicadasOutput;
import com.senai.back.entities.User;
import com.senai.back.entities.Vacinas;
import com.senai.back.entities.VacinasAplicadas;
import com.senai.back.repositories.UserRepository;
import com.senai.back.repositories.VacinasAplicadasRepository;
import com.senai.back.repositories.VacinasRetository;


@Service
public class VacinasAplicadasService {
    @Autowired
    private VacinasAplicadasRepository repository;

    @Autowired
    private VacinasRetository vacinasRetository;

    @Autowired
    private UserRepository userRepository;

    public VacinasAplicadasOutput create(Long id, VacinasAplicadasInput input){
        if(userRepository.existsById(id)){
            User user = userRepository.findByEmail(input.getEmailUser());
            User profissonal = userRepository.findById(id).get();
            Vacinas vacina = vacinasRetository.findByLote(input.getLoteVacina());
            VacinasAplicadas aplicoes = convertInputToAplicacao(input);
         
            aplicoes.setUser(user);
            aplicoes.setProfissional(profissonal);
            aplicoes.setVacina(vacina);
            repository.save(aplicoes);
        
            return convertAplicacaoToOutput(aplicoes);
        }
        
        return null;
    }

    public VacinasAplicadasOutput read(Long id){
        VacinasAplicadas vacinas = repository.findById(id).orElse(null);

        return convertAplicacaoToOutput(vacinas);

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

    

    private VacinasAplicadas convertInputToAplicacao(VacinasAplicadasInput input){
        VacinasAplicadas vacinas = new VacinasAplicadas();
            vacinas.setDataAplicação(input.getDataAplicação());
            vacinas.setQuantidadeDoses(input.getQuantidadeDoses());
            vacinas.setDataRetorno(input.getDataRetorno());
        return vacinas;

    }

    private VacinasAplicadasOutput convertAplicacaoToOutput(VacinasAplicadas vacinas){
        if(vacinas == null){
            return null;
        }

        VacinasAplicadasOutput output = new VacinasAplicadasOutput(
            vacinas.getId(),
            vacinas.getDataAplicação(),
            vacinas.getQuantidadeDoses(),
            vacinas.getDataRetorno(),
            vacinas.getUser(),
            vacinas.getProfissional(),
            vacinas.getVacina()
            
        );
        return output;

    }
}
