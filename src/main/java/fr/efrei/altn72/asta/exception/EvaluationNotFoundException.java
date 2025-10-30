package fr.efrei.altn72.asta.exception;

public class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException(Long id) {
        super("Évaluation introuvable avec l'identifiant : " + id);
    }

    public EvaluationNotFoundException(String message) {
        super(message);
    }
}
