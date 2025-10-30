package fr.efrei.altn72.asta.controller;

import fr.efrei.altn72.asta.model.Apprenti;
import fr.efrei.altn72.asta.service.ApprentiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fr.efrei.altn72.asta.model.Entreprise;
import fr.efrei.altn72.asta.model.Mission;
import fr.efrei.altn72.asta.model.Visite;
import fr.efrei.altn72.asta.model.EvaluationEcole;


import java.util.List;

@Controller
@RequestMapping("/apprentis")
public class ApprentiController {

    private final ApprentiService apprentiService;

    @Autowired
    public ApprentiController(ApprentiService apprentiService) {
        this.apprentiService = apprentiService;
    }

    // 🟢 Liste des apprentis (Vue Thymeleaf)
    @GetMapping
    public String listApprentis(Model model, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        List<Apprenti> apprentis = apprentiService.getAllApprentis();
        model.addAttribute("apprentis", apprentis);
        return "apprentis";
    }

    // 🟢 Recherche d'apprentis (nom / entreprise / mot-clé / année)
    @GetMapping("/search")
    public String searchApprentis(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String entreprise,
            @RequestParam(required = false) String motCle,
            @RequestParam(required = false) String annee,
            Model model,
            HttpSession session) {

        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        List<Apprenti> apprentis = apprentiService.search(nom, entreprise, motCle, annee);
        model.addAttribute("apprentis", apprentis);
        return "apprentis";
    }

    // 🟢 Afficher le formulaire d'ajout
    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        model.addAttribute("apprenti", new Apprenti());
        return "apprenti-form";
    }

    // 🟢 Soumission du formulaire d'ajout
    @PostMapping("/save")
    public String saveApprenti(@ModelAttribute Apprenti apprenti, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        apprentiService.createApprenti(apprenti);
        return "redirect:/apprentis";
    }

    // 🟢 Formulaire de modification
    @GetMapping("/edit/{id}")
    public String editApprenti(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        Apprenti apprenti = apprentiService.getApprentiById(id);
        model.addAttribute("apprenti", apprenti);
        return "apprenti-form";
    }

    // 🟢 Soumission du formulaire de modification
    @PostMapping("/update/{id}")
    public String updateApprenti(@PathVariable Long id, @ModelAttribute Apprenti apprenti, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        apprentiService.updateApprenti(id, apprenti);
        return "redirect:/apprentis";
    }

    // 🟢 Supprimer un apprenti (Vue)
    @GetMapping("/delete/{id}")
    public String deleteApprenti(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        apprentiService.deleteApprenti(id);
        return "redirect:/apprentis";
    }

    // 🟢 Détails d'un apprenti (Vue)
    @GetMapping("/{id}")
    public String showApprentiDetail(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("prenom") == null) {
            return "redirect:/login";
        }

        Apprenti apprenti = apprentiService.getApprentiWithRelations(id);  // Utilise la méthode du service
        model.addAttribute("apprenti", apprenti);
        return "apprenti-detail";  // La page de détails de l'apprenti
    }


    // 🔵 === API REST JSON ===

    @GetMapping("/api")
    @ResponseBody
    public List<Apprenti> getAllApprentisAPI() {
        return apprentiService.getAllApprentis();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Apprenti> getApprentiByIdAPI(@PathVariable Long id) {
        return ResponseEntity.ok(apprentiService.getApprentiById(id));
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Apprenti> createApprentiAPI(@RequestBody Apprenti apprenti) {
        return ResponseEntity.ok(apprentiService.createApprenti(apprenti));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Apprenti> updateApprentiAPI(@PathVariable Long id, @RequestBody Apprenti apprenti) {
        return ResponseEntity.ok(apprentiService.updateApprenti(id, apprenti));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteApprentiAPI(@PathVariable Long id) {
        apprentiService.deleteApprenti(id);
        return ResponseEntity.noContent().build();
    }
}
