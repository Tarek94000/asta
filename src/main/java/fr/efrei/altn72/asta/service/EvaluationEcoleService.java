package fr.efrei.altn72.asta.service;

import fr.efrei.altn72.asta.exception.EvaluationNotFoundException;
import fr.efrei.altn72.asta.model.EvaluationEcole;
import fr.efrei.altn72.asta.repository.EvaluationEcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationEcoleService {

    private final EvaluationEcoleRepository evaluationEcoleRepository;

    @Autowired
    public EvaluationEcoleService(EvaluationEcoleRepository evaluationEcoleRepository) {
        this.evaluationEcoleRepository = evaluationEcoleRepository;
    }

    // ✅ Récupérer toutes les évaluations
    public List<EvaluationEcole> getAllEvaluations() {
        return evaluationEcoleRepository.findAll();
    }

    // ✅ Récupérer une évaluation par ID
    public EvaluationEcole getEvaluationById(Long id) {
        return evaluationEcoleRepository.findById(id)
                .orElseThrow(() -> new EvaluationNotFoundException(id));
    }

    // ✅ Créer une nouvelle évaluation
    public EvaluationEcole createEvaluation(EvaluationEcole evaluationEcole) {
        return evaluationEcoleRepository.save(evaluationEcole);
    }

    // ✅ Mettre à jour une évaluation existante
    public EvaluationEcole updateEvaluation(Long id, EvaluationEcole updatedEvaluation) {
        EvaluationEcole existing = getEvaluationById(id);
        existing.setTheme(updatedEvaluation.getTheme());
        existing.setNoteFinale(updatedEvaluation.getNoteFinale());
        existing.setCommentaires(updatedEvaluation.getCommentaires());
        existing.setApprenti(updatedEvaluation.getApprenti());
        return evaluationEcoleRepository.save(existing);
    }

    // ✅ Supprimer une évaluation
    public void deleteEvaluation(Long id) {
        if (!evaluationEcoleRepository.existsById(id)) {
            throw new EvaluationNotFoundException("Impossible de supprimer : évaluation non trouvée avec l'id " + id);
        }
        evaluationEcoleRepository.deleteById(id);
    }
}
