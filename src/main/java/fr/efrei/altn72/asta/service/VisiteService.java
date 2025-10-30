package fr.efrei.altn72.asta.service;

import fr.efrei.altn72.asta.exception.VisiteNotFoundException;
import fr.efrei.altn72.asta.model.Visite;
import fr.efrei.altn72.asta.repository.VisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisiteService {

    private final VisiteRepository visiteRepository;

    @Autowired
    public VisiteService(VisiteRepository visiteRepository) {
        this.visiteRepository = visiteRepository;
    }

    // ✅ Récupérer toutes les visites
    public List<Visite> getAllVisites() {
        return visiteRepository.findAll();
    }

    // ✅ Récupérer une visite par ID
    public Visite getVisiteById(Long id) {
        return visiteRepository.findById(id)
                .orElseThrow(() -> new VisiteNotFoundException(id));
    }

    // ✅ Créer une nouvelle visite
    public Visite createVisite(Visite visite) {
        return visiteRepository.save(visite);
    }

    // ✅ Mettre à jour une visite existante
    public Visite updateVisite(Long id, Visite updatedVisite) {
        Visite existing = getVisiteById(id);
        existing.setDate(updatedVisite.getDate());
        existing.setFormat(updatedVisite.getFormat());
        existing.setCommentaires(updatedVisite.getCommentaires());
        existing.setApprenti(updatedVisite.getApprenti());
        return visiteRepository.save(existing);
    }

    // ✅ Supprimer une visite
    public void deleteVisite(Long id) {
        if (!visiteRepository.existsById(id)) {
            throw new VisiteNotFoundException("Impossible de supprimer : visite non trouvée avec l'id " + id);
        }
        visiteRepository.deleteById(id);
    }
}
