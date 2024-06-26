package com.sa.back.tests;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.senai.back.dtos.carimbos.CarimboInput;
import com.senai.back.dtos.carimbos.CarimboOutput;
import com.senai.back.entities.User;
import com.senai.back.entities.Vacinas;
import com.senai.back.entities.enums.Categoria;
import com.senai.back.entities.enums.Genero;
import com.senai.back.repositories.CarimboRepository;
import com.senai.back.repositories.UserRepository;
import com.senai.back.repositories.VacinasRetository;
import com.senai.back.services.CarimboService;

public class CarimboServiceTeste {
    @Mock
    private CarimboRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VacinasRetository vacinasRetository;

    @InjectMocks
    private CarimboService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCarimbar() {
        Long profissionalId = 1l;
        LocalDate date = LocalDate.now();
        CarimboInput input = new CarimboInput(date, "N235", "user@gmail.com");

        // userRepository.findById(profissionalId);
        // userRepository.findByEmail(input.getEmailuser());
        // vacinasRetository.findByLote(input.getLote());

        User userProfissional = new User();

        userProfissional.setId(1l);
        userProfissional.setNomeCompleto("Wesley Costa");
        userProfissional.setDataNascimento("2001-07-16");
        userProfissional.setGenero(Genero.MASCULINO);
        userProfissional.setCpf("90730234612");
        userProfissional.setEmail("user2@gmail.com");
        userProfissional.setSenha("12345676");
        userProfissional.setCategoria(Categoria.ADULTO);
        userProfissional.setNumeroCoren("780231");

        User user = new User();

        user.setId(2l);
        user.setNomeCompleto("Kauam luiz vieira de souza");
        user.setDataNascimento("2006-08-29");
        user.setGenero(Genero.MASCULINO);
        user.setCpf("24429347507");
        user.setEmail("user@gmail.com");
        user.setSenha("12345676");
        user.setCategoria(Categoria.ADOLESCENTE);

        Vacinas vacina = new Vacinas(1l, "sarampo", "N235");

        CarimboOutput output = new CarimboOutput();
        output.setId(1l);
        output.setProfissional(userProfissional);
        output.setUser(user);
        output.setVacinas(vacina);

        // when do profissional
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userProfissional));
        // when do usuario
        when(userRepository.findByEmail(input.getEmailuser())).thenReturn(user);
        // when da vacina
        when(vacinasRetository.findByLote(input.getLote())).thenReturn(vacina);

        // chamar o metodo do service
        CarimboOutput resultado = service.Carimbar(profissionalId, input);
        System.out.println(resultado.toString());

        // fazer os asserts
        Assertions.assertEquals(output.getProfissional().getId(), resultado.getProfissional().getId());
        Assertions.assertEquals(output.getProfissional().getNomeCompleto(),
                resultado.getProfissional().getNomeCompleto());
        Assertions.assertEquals(output.getProfissional().getDataNascimento(),
                resultado.getProfissional().getDataNascimento());
        Assertions.assertEquals(output.getProfissional().getGenero(), resultado.getProfissional().getGenero());
        Assertions.assertEquals(output.getProfissional().getCpf(), resultado.getProfissional().getCpf());
        Assertions.assertEquals(output.getProfissional().getEmail(), resultado.getProfissional().getEmail());
        Assertions.assertEquals(output.getProfissional().getSenha(), resultado.getProfissional().getSenha());
        Assertions.assertEquals(output.getProfissional().getCategoria(), resultado.getProfissional().getCategoria());
        Assertions.assertEquals(output.getProfissional().getNumeroCoren(),
                resultado.getProfissional().getNumeroCoren());

        Assertions.assertEquals(output.getUser().getId(), resultado.getUser().getId());
        Assertions.assertEquals(output.getUser().getNomeCompleto(), resultado.getUser().getNomeCompleto());
        Assertions.assertEquals(output.getUser().getDataNascimento(), resultado.getUser().getDataNascimento());
        Assertions.assertEquals(output.getUser().getGenero(), resultado.getUser().getGenero());
        Assertions.assertEquals(output.getUser().getCpf(), resultado.getUser().getCpf());
        Assertions.assertEquals(output.getUser().getEmail(), resultado.getUser().getEmail());
        Assertions.assertEquals(output.getUser().getSenha(), resultado.getUser().getSenha());
        Assertions.assertEquals(output.getUser().getCategoria(), resultado.getUser().getCategoria());

        Assertions.assertEquals(output.getVacinas().getId(), resultado.getVacinas().getId());
        Assertions.assertEquals(output.getVacinas().getNomeVacina(), resultado.getVacinas().getNomeVacina());
        Assertions.assertEquals(output.getVacinas().getLote(), resultado.getVacinas().getLote());

    }
}
