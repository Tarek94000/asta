package fr.efrei.altn72.asta.exception;

public class ApprentiNotFoundException extends RuntimeException {
    public ApprentiNotFoundException(Long id) {
        super("Apprenti introuvable avec l'identifiant : " + id);
    }

    public ApprentiNotFoundException(String message) {
        super(message);
    }
}
