package com.maska.h_l.repository;

import com.maska.h_l.domain.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    Page<Participation> findAll(Pageable pageable);
    Participation findByUserIdAndCompetitionId(UUID userId, UUID competitionId);
    @Modifying
    @Query("DELETE FROM Participation p WHERE p.user.id = :userId")
    void deleteAllByUserId(UUID userId);
}
