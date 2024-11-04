package com.maska.h_l.service;

import com.maska.h_l.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationService {

    public final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public void deleteAllByUserId(UUID userId) {
        participationRepository.deleteAllByUserId(userId);
    }
}
