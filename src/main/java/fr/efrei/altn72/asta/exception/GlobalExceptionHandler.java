package fr.efrei.altn72.asta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --- Exemple : Apprenti non trouvé ---
    @ExceptionHandler(ApprentiNotFoundException.class)
    public ModelAndView handleApprentiNotFound(ApprentiNotFoundException ex) {
        return buildErrorPage(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // --- Autres erreurs personnalisées ---
    @ExceptionHandler({
            EntrepriseNotFoundException.class,
            MissionNotFoundException.class,
            EvaluationNotFoundException.class,
            VisiteNotFoundException.class
    })
    public ModelAndView handleEntityNotFound(RuntimeException ex) {
        return buildErrorPage(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // --- Erreurs générales ---
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneric(Exception ex) {
        return buildErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                "Une erreur inattendue est survenue : " + ex.getMessage());
    }

    // --- Méthode utilitaire ---
    private ModelAndView buildErrorPage(HttpStatus status, String message) {
        ModelAndView mav = new ModelAndView("error"); // error.html
        mav.addObject("status", status.value());
        mav.addObject("message", message);
        return mav;
    }
}
