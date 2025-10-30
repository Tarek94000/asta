package fr.efrei.altn72.asta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Visite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String format;
    private String commentaires;
    private String date;

    // Ajout de la relation ManyToOne vers Apprenti
    @ManyToOne
    @JoinColumn(name = "apprenti_id") // la clé étrangère
    private Apprenti apprenti;  // Liens vers l'apprenti
}
