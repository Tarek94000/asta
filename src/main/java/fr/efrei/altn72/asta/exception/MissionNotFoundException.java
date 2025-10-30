package fr.efrei.altn72.asta.exception;

public class MissionNotFoundException extends RuntimeException {
    public MissionNotFoundException(Long id) {
        super("Mission introuvable avec l'identifiant : " + id);
    }

    public MissionNotFoundException(String message) {
        super(message);
    }
}
