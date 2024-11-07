package com.maska.h_l.service;

import com.maska.h_l.domain.Species;
import com.maska.h_l.repository.SpecieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpecieService {
    private final SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }


    public Page<Species> getAllSpecies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return specieRepository.findAll(pageable);
    }


    public Species saveSpecie(Species species) {
        return specieRepository.save(species);
    }

    public Species updateSpecie(Species species) {
        return specieRepository.save(species);
    }

    public void deleteSpecie(UUID id) {
        specieRepository.deleteById(id);
    }

    public Optional<Species> getSpecieById(UUID id) {
        return specieRepository.findById(id);
    }


}
