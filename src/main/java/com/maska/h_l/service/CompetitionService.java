package com.maska.h_l.service;

import com.maska.h_l.domain.Competition;
import com.maska.h_l.repository.CompetitionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Page<Competition> getAllCompetitions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return competitionRepository.findAll(pageable);
    }

    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Optional<Competition> getCompetitionById(UUID id) {
        return competitionRepository.findById(id);
    }

    public void deleteCompetition(UUID competitionId) {
        competitionRepository.deleteById(competitionId);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

}
