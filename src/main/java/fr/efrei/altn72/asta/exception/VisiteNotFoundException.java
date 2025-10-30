package fr.efrei.altn72.asta.exception;

public class VisiteNotFoundException extends RuntimeException {
    public VisiteNotFoundException(Long id) {
        super("Visite introuvable avec l'identifiant : " + id);
    }

    public VisiteNotFoundException(String message) {
        super(message);
    }
}
