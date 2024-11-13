package com.maska.h_l.service;

import com.maska.h_l.domain.Participation;
import com.maska.h_l.repository.ParticipationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationService {

    public final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Page<Participation> getAllParticipations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return participationRepository.findAll(pageable);
    }

    public Participation saveParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public void deleteAllByUserId(UUID userId) {
        participationRepository.deleteAllByUserId(userId);
    }

    public Participation findByUserIdAndCompetitionId(UUID userId, UUID competitionId) {
        return participationRepository.findByUserIdAndCompetitionId(userId, competitionId);
    }
}
