package com.maska.h_l.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "participation", indexes = {
        @Index(name = "idx_participation_user_id", columnList = "user_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participation{

    @Id @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Competition competition;

    @OneToMany(mappedBy = "participation")
    private List<Hunt> hunts;

    private Double score;

}
