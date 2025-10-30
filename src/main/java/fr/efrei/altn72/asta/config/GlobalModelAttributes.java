package fr.efrei.altn72.asta.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class GlobalModelAttributes {

    @ModelAttribute("prenom")
    public String addPrenomAttribute(HttpSession session) {
        return (String) session.getAttribute("prenom");
    }
}
