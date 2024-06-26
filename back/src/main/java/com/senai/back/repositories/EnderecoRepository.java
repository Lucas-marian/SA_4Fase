package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    
}
