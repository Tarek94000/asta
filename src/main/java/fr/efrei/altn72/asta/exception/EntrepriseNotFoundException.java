package fr.efrei.altn72.asta.exception;

public class EntrepriseNotFoundException extends RuntimeException {
    public EntrepriseNotFoundException(Long id) {
        super("Entreprise introuvable avec l'identifiant : " + id);
    }

    public EntrepriseNotFoundException(String message) {
        super(message);
    }
}
