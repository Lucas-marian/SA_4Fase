package com.senai.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.back.dtos.carimbos.CarimboInput;
import com.senai.back.dtos.carimbos.CarimboOutput;
import com.senai.back.entities.Carimbo;
import com.senai.back.entities.User;
import com.senai.back.entities.Vacinas;
import com.senai.back.repositories.CarimboRepository;
import com.senai.back.repositories.UserRepository;
import com.senai.back.repositories.VacinasRetository;

@Service
public class CarimboService {
    @Autowired
    private CarimboRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VacinasRetository vacinasRetository;

    public CarimboOutput Carimbar(Long profissinalId, CarimboInput input) {
        if (userRepository.existsById(profissinalId)) {
            User profissonal = userRepository.findById(profissinalId).get();
            User user = userRepository.findByEmail(input.getEmailuser());
            Vacinas vacina = vacinasRetository.findByLote(input.getLote());
            Carimbo carimbo = convertInputToCarimbo(input);

            carimbo.setVacinas(vacina);
            carimbo.setUser(user);
            carimbo.setProfissional(profissonal);
            repository.save(carimbo);

            return convertCarimboToOutput(carimbo);
        }

        return null;
    }

    public CarimboOutput read(Long id){
        Carimbo carimbo = repository.findById(id).orElse(null);

        return convertCarimboToOutput(carimbo);

    }

////////////////////////
    private Carimbo convertInputToCarimbo(CarimboInput input) {
        Carimbo carimbo = new Carimbo();
        carimbo.setData(input.getData());
        return carimbo;

    }

    private CarimboOutput convertCarimboToOutput(Carimbo carimbo) {
        if (carimbo == null) {
            return null;
        }

        CarimboOutput output = new CarimboOutput(
                carimbo.getId(),
                carimbo.getData(),
                carimbo.getProfissional(),
                carimbo.getUser(),
                carimbo.getVacinas()

        );
        return output;

    }
}
