package com.maska.h_l.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "hunt", indexes = {
        @Index(name = "idx_hunt_species_id", columnList = "species_id"),
        @Index(name = "idx_hunt_participation_id", columnList = "participation_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hunt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "species_id") // Définir le nom de la colonne pour l'indexation
    private Species species;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "participation_id") // Définir le nom de la colonne pour l'indexation
    private Participation participation;
}
