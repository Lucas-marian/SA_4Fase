package com.senai.back.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.senai.back.dtos.enderecos.EnderecoInput;
import com.senai.back.dtos.enderecos.EnderecoOutput;
import com.senai.back.entities.Endereco;
import com.senai.back.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    @Transactional
    public EnderecoOutput create(EnderecoInput input){
        Endereco endereco = convertInputToEndereco(input);
        endereco = repository.save(endereco);

        return convertEnderecoToOutput(endereco);

    }

    public List<EnderecoOutput> list(){
        return repository
            .findAll()
            .stream()
            .map(u -> convertEnderecoToOutput(u))
            .toList();

    }

    public EnderecoOutput read(Long id){
        Endereco endereco = repository.findById(id).orElse(null);

        return convertEnderecoToOutput(endereco);

    }

    @Transactional
    public EnderecoOutput update(Long id, EnderecoInput input){
        if(repository.existsById(id)){
            Endereco endereco = convertInputToEndereco(input);
                endereco.setId(id);
            endereco = repository.save(endereco);
            return convertEnderecoToOutput(endereco);

        }else {
            return null;

        }

    }

    public void delete(Long id){
        repository.deleteById(id);

    }

//////// conversão
    public Endereco convertInputToEndereco(EnderecoInput input){
        Endereco endereco = new Endereco();
            endereco.setNomeRua(input.getNomeRua());
            endereco.setNumeroCasa(input.getNumeroCasa());
            endereco.setComplemento(input.getComplemento());
            endereco.setCep(input.getCep());
            endereco.setBairro(input.getBairro());
            endereco.setCidade(input.getCidade());
            endereco.setEstado(input.getEstado());
            endereco.setInterior(input.getInterior());
        return endereco;

    }

    public EnderecoOutput convertEnderecoToOutput(Endereco endereco){
        if(endereco == null){
            return null;
        }

        EnderecoOutput output = new EnderecoOutput(
            endereco.getId(),
            endereco.getNomeRua(),
            endereco.getNumeroCasa(),
            endereco.getComplemento(),
            endereco.getCep(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getInterior()

        );
        return output;

    }
////////
}
