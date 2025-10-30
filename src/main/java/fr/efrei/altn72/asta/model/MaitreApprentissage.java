package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MaitreApprentissage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String poste;
    private String email;
    private String telephone;
    private String remarques;
}
