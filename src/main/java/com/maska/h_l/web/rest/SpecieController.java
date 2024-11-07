package com.maska.h_l.web.rest;


import com.maska.h_l.domain.Species;
import com.maska.h_l.service.SpecieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpecieController {
    private final SpecieService specieService;

    public SpecieController(SpecieService specieService) {
        this.specieService = specieService;
    }

    @GetMapping
    public Page<Species> getAllSpecies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return specieService.getAllSpecies(page, size);
    }

    @PostMapping
    public Species createSpecie(@RequestBody Species species) {
        return specieService.saveSpecie(species);
    }

    @GetMapping("/id")
    public ResponseEntity<Species> getSpecieById(@RequestParam UUID id) {
        return specieService.getSpecieById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Species> updateSpecie(@RequestParam UUID id, @RequestBody Species species) {
        species.setId(id);
        Species updatedSpecie = specieService.updateSpecie(species);
        return ResponseEntity.ok(updatedSpecie);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSpecie(@RequestParam UUID id) {
        specieService.deleteSpecie(id);
        return ResponseEntity.noContent().build();
    }

}
