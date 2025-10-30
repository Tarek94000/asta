package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EvaluationEcole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private String noteFinale;
    private String commentaires;

    @ManyToOne
    @JoinColumn(name = "apprenti_id")
    private Apprenti apprenti;  // Relation vers Apprenti
}
