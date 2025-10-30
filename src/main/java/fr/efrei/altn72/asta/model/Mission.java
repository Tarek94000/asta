package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metierCible;
    private String motsCles;  // Champs pour les mots-clés
    private String commentaires;

    @ManyToOne
    @JoinColumn(name = "apprenti_id")
    private Apprenti apprenti; // Relation vers Apprenti
}
