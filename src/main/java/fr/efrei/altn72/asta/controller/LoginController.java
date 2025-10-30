package fr.efrei.altn72.asta.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Identifiants fictifs du tuteur enseignant
    private static final String LOGIN = "tuteur";
    private static final String PASSWORD = "password";

    // 🟢 Page de connexion
    @GetMapping({"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("title", "Connexion - ASTA");
        return "login";
    }

    // 🟢 Traitement du formulaire
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (LOGIN.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("prenom", "Test"); // prénom affiché en haut
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Identifiants incorrects !");
            return "login";
        }
    }

    // 🔴 Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
