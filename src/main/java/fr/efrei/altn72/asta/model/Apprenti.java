package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Apprenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String anneeAcademique;
    private String programme;
    private String majeure;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise; // Relation vers Entreprise

    @OneToOne(cascade = CascadeType.ALL)
    private Mission mission; // Relation vers Mission

    @OneToOne(cascade = CascadeType.ALL)
    private Visite visite; // Relation vers Visite

    @OneToOne(cascade = CascadeType.ALL)
    private EvaluationEcole evaluationEcole; // Relation vers EvaluationEcole
}

