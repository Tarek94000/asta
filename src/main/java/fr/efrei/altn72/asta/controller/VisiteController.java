package fr.efrei.altn72.asta.controller;

import fr.efrei.altn72.asta.model.Visite;
import fr.efrei.altn72.asta.service.VisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visites")
public class VisiteController {

    private final VisiteService visiteService;

    @Autowired
    public VisiteController(VisiteService visiteService) {
        this.visiteService = visiteService;
    }

    @GetMapping
    public List<Visite> getAll() {
        return visiteService.getAllVisites();
    }

    @GetMapping("/{id}")
    public Visite getById(@PathVariable Long id) {
        return visiteService.getVisiteById(id);
    }

    @PostMapping
    public Visite create(@RequestBody Visite visite) {
        return visiteService.createVisite(visite);
    }

    @PutMapping("/{id}")
    public Visite update(@PathVariable Long id, @RequestBody Visite visite) {
        return visiteService.updateVisite(id, visite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visiteService.deleteVisite(id);
        return ResponseEntity.noContent().build();
    }
}
