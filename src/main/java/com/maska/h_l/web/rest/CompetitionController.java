package com.maska.h_l.web.rest;


import com.maska.h_l.domain.Competition;
import com.maska.h_l.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public Page<Competition> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return competitionService.getAllCompetitions(page, size);
    }

    @PostMapping
    public Competition createCompetition(@RequestBody Competition competition) {
        return competitionService.saveCompetition(competition);
    }

    @GetMapping("/id")
    public ResponseEntity<Competition> getCompetitionById(@RequestParam UUID id) {
        return competitionService.getCompetitionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Competition> updateCompetition(@RequestParam UUID id, @RequestBody Competition competition) {
        competition.setId(id);
        Competition updatedCompetition = competitionService.updateCompetition(competition);
        return ResponseEntity.ok(updatedCompetition);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCompetition(@RequestParam UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUserToCompetition(@RequestParam UUID userId, @RequestParam UUID competitionId) {
        String responseMessage = competitionService.registerUserToCompetition(userId, competitionId);
        if (responseMessage.contains("successfully")) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

}
