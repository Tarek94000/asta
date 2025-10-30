package fr.efrei.altn72.asta.service;

import fr.efrei.altn72.asta.exception.ApprentiNotFoundException;
import fr.efrei.altn72.asta.exception.EntrepriseNotFoundException;
import fr.efrei.altn72.asta.model.Apprenti;
import fr.efrei.altn72.asta.model.Entreprise;
import fr.efrei.altn72.asta.repository.ApprentiRepository;
import fr.efrei.altn72.asta.repository.EntrepriseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Service
public class ApprentiService {

    private final ApprentiRepository apprentiRepository;
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public ApprentiService(ApprentiRepository apprentiRepository, EntrepriseRepository entrepriseRepository) {
        this.apprentiRepository = apprentiRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    // ✅ Lire tous les apprentis
    public List<Apprenti> getAllApprentis() {
        return apprentiRepository.findAll();
    }

    // ✅ Lire un apprenti par ID
    public Apprenti getApprentiById(Long id) {
        return apprentiRepository.findById(id)
                .orElseThrow(() -> new ApprentiNotFoundException(id));
    }

    // ✅ Créer un apprenti
    public Apprenti createApprenti(Apprenti apprenti) {
        apprenti.setEntreprise(resolveEntreprise(apprenti.getEntreprise()));
        return apprentiRepository.save(apprenti);
    }

    // ✅ Mettre à jour un apprenti
    public Apprenti updateApprenti(Long id, Apprenti updatedApprenti) {
        Apprenti existing = getApprentiById(id);
        existing.setNom(updatedApprenti.getNom());
        existing.setPrenom(updatedApprenti.getPrenom());
        existing.setEmail(updatedApprenti.getEmail());
        existing.setTelephone(updatedApprenti.getTelephone());
        existing.setAnneeAcademique(updatedApprenti.getAnneeAcademique());
        existing.setProgramme(updatedApprenti.getProgramme());
        existing.setMajeure(updatedApprenti.getMajeure());
        existing.setEntreprise(resolveEntreprise(updatedApprenti.getEntreprise()));
        existing.setMission(updatedApprenti.getMission());
        existing.setEvaluationEcole(updatedApprenti.getEvaluationEcole());
        existing.setVisite(updatedApprenti.getVisite());
        return apprentiRepository.save(existing);
    }

    // ✅ Supprimer un apprenti
    public void deleteApprenti(Long id) {
        if (!apprentiRepository.existsById(id)) {
            throw new ApprentiNotFoundException("Impossible de supprimer : apprenti non trouvé avec l'id " + id);
        }
        apprentiRepository.deleteById(id);
    }

    // ✅ Recherche par nom
    public List<Apprenti> searchByNom(String nom) {
        return apprentiRepository.findByNomContainingIgnoreCase(nom);
    }

    // ✅ Recherche par entreprise
    public List<Apprenti> searchByEntreprise(String raisonSociale) {
        return apprentiRepository.findByEntreprise_RaisonSociale(raisonSociale);
    }

    // ✅ Recherche multi-critères
    public List<Apprenti> search(String nom, String entreprise, String motCle, String annee) {
        if (nom != null && !nom.isEmpty()) {
            return apprentiRepository.findByNomContainingIgnoreCase(nom);
        } else if (entreprise != null && !entreprise.isEmpty()) {
            return apprentiRepository.findByEntreprise_RaisonSociale(entreprise);
        } else if (motCle != null && !motCle.isEmpty()) {
            return apprentiRepository.findByMission_MotsClesContainingIgnoreCase(motCle);
        } else if (annee != null && !annee.isEmpty()) {
            return apprentiRepository.findByAnneeAcademique(annee);
        }
        return apprentiRepository.findAll();
    }

    // ✅ Nouvelle année académique (promotion / archivage)
    @Transactional
    public void creerNouvelleAnnee(String nouvelleAnnee) {
        List<Apprenti> apprentis = apprentiRepository.findAll();

        for (Apprenti a : apprentis) {
            switch (a.getProgramme()) {
                case "I1" -> a.setProgramme("I2");
                case "I2" -> a.setProgramme("I3");
                case "I3" -> a.setProgramme("ARCHIVE"); // archivé
            }
            a.setAnneeAcademique(nouvelleAnnee);
            apprentiRepository.save(a);
        }
    }

    public Apprenti getApprentiWithRelations(Long id) {
        return apprentiRepository.findByIdWithRelations(id);
    }


    private Entreprise resolveEntreprise(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }

        if (entreprise.getId() != null) {
            return entrepriseRepository.findById(entreprise.getId())
                    .orElseThrow(() -> new EntrepriseNotFoundException(entreprise.getId()));
        }

        String raisonSociale = entreprise.getRaisonSociale();
        if (raisonSociale == null || raisonSociale.isBlank()) {
            return entreprise;
        }

        Optional<Entreprise> existing = entrepriseRepository.findByRaisonSocialeIgnoreCase(raisonSociale.trim());
        if (existing.isPresent()) {
            Entreprise managed = existing.get();
            managed.setAdresse(entreprise.getAdresse());
            managed.setInformationsAcces(entreprise.getInformationsAcces());
            managed.setMaitreApprentissage(entreprise.getMaitreApprentissage());
            return managed;
        }

        return entreprise;
    }

}
