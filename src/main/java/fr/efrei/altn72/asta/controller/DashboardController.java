package fr.efrei.altn72.asta.controller;

import fr.efrei.altn72.asta.model.Apprenti;
import fr.efrei.altn72.asta.service.ApprentiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class DashboardController {

    private final ApprentiService apprentiService;

    @Autowired
    public DashboardController(ApprentiService apprentiService) {
        this.apprentiService = apprentiService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String prenom = (String) session.getAttribute("prenom");
        if (prenom == null) {
            return "redirect:/login";
        }

        List<Apprenti> apprentis = apprentiService.getAllApprentis();
        model.addAttribute("prenom", prenom);
        model.addAttribute("apprentis", apprentis);
        model.addAttribute("nbApprentis", apprentis.size());
        model.addAttribute("title", "Tableau de bord");
        return "dashboard";
    }
    @PostMapping("/annee/nouvelle")
    public String nouvelleAnnee(@RequestParam String annee, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }
        apprentiService.creerNouvelleAnnee(annee);
        return "redirect:/dashboard";
    }

}
