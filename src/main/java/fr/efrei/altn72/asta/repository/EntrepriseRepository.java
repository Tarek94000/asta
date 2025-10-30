package fr.efrei.altn72.asta.repository;

import fr.efrei.altn72.asta.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Optional<Entreprise> findByRaisonSocialeIgnoreCase(String raisonSociale);
}
