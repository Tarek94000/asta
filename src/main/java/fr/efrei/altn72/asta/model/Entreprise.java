package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisonSociale;
    private String adresse;
    @Column(name = "informations_acces")
    private String informationsAcces;

    @ManyToOne
    private MaitreApprentissage maitreApprentissage; // Relation avec le MaitreApprentissage
}
