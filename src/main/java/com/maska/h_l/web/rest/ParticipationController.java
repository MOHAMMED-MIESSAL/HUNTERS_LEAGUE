package com.maska.h_l.web.rest;


import com.maska.h_l.domain.Participation;
import com.maska.h_l.service.ParticipationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping
    public Page<Participation> getAllParticipations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size) {
        return participationService.getAllParticipations(page, size);
    }

    @GetMapping("/findByUserIdAndCompetitionId")
    public Participation findByUserIdAndCompetitionId(
            @RequestParam UUID userId,
            @RequestParam UUID competitionId) {
        return participationService.findByUserIdAndCompetitionId(userId, competitionId);
    }

}
