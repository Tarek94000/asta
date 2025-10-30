package fr.efrei.altn72.asta.service;

import fr.efrei.altn72.asta.exception.EntrepriseNotFoundException;
import fr.efrei.altn72.asta.model.Entreprise;
import fr.efrei.altn72.asta.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    // ✅ Récupérer toutes les entreprises
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    // ✅ Récupérer une entreprise par ID
    public Entreprise getEntrepriseById(Long id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new EntrepriseNotFoundException(id));
    }

    // ✅ Créer une entreprise
    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    // ✅ Mettre à jour une entreprise
    public Entreprise updateEntreprise(Long id, Entreprise updatedEntreprise) {
        Entreprise existing = getEntrepriseById(id);
        existing.setRaisonSociale(updatedEntreprise.getRaisonSociale());
        existing.setAdresse(updatedEntreprise.getAdresse());
        existing.setInformationsAcces(updatedEntreprise.getInformationsAcces());
        existing.setMaitreApprentissage(updatedEntreprise.getMaitreApprentissage());
        return entrepriseRepository.save(existing);
    }

    // ✅ Supprimer une entreprise
    public void deleteEntreprise(Long id) {
        if (!entrepriseRepository.existsById(id)) {
            throw new EntrepriseNotFoundException("Impossible de supprimer : entreprise non trouvée avec l'id " + id);
        }
        entrepriseRepository.deleteById(id);
    }
}
