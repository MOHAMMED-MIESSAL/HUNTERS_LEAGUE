package com.maska.h_l.service;

import com.maska.h_l.domain.Competition;
import com.maska.h_l.domain.Participation;
import com.maska.h_l.domain.User;
import com.maska.h_l.repository.CompetitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ParticipationService participationService;
    private final UserService userService;


    public CompetitionService(CompetitionRepository competitionRepository, ParticipationService participationService, UserService userService) {
        this.competitionRepository = competitionRepository;
        this.participationService = participationService;
        this.userService = userService;
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

    @Transactional
    public String registerUserToCompetition(UUID userId, UUID competitionId) {
        Optional<User> userOptional = userService.getUserById(userId);
        Optional<Competition> competitionOptional = competitionRepository.findById(competitionId);

        if (userOptional.isEmpty()) {
            return "User not found!";
        }
        if (competitionOptional.isEmpty()) {
            return "Competition not found!";
        }

        User user = userOptional.get();
        Competition competition = competitionOptional.get();

        // Créer et sauvegarder la participation
        Participation participation = new Participation();
        participation.setUser(user);
        participation.setCompetition(competition);

        participationService.saveParticipation(participation);

        return "User successfully registered for the competition!";
    }
}
