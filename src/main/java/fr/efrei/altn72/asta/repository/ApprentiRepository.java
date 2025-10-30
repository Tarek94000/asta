package fr.efrei.altn72.asta.repository;

import fr.efrei.altn72.asta.model.Apprenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    // Requête personnalisée pour récupérer un apprenti avec toutes ses relations
    @Query("SELECT a FROM Apprenti a " +
            "LEFT JOIN FETCH a.entreprise " +
            "LEFT JOIN FETCH a.mission " +
            "LEFT JOIN FETCH a.visite " +
            "LEFT JOIN FETCH a.evaluationEcole " +
            "WHERE a.id = :id")
    Apprenti findByIdWithRelations(@Param("id") Long id);

    List<Apprenti> findByNomContainingIgnoreCase(String nom);

    List<Apprenti> findByEntreprise_RaisonSociale(String raisonSociale);

    List<Apprenti> findByMission_MotsClesContainingIgnoreCase(String motCle);

    List<Apprenti> findByAnneeAcademique(String annee);
}
