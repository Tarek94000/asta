package fr.efrei.altn72.asta.controller;

import fr.efrei.altn72.asta.model.EvaluationEcole;
import fr.efrei.altn72.asta.service.EvaluationEcoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationEcoleController {

    private final EvaluationEcoleService evaluationEcoleService;

    @Autowired
    public EvaluationEcoleController(EvaluationEcoleService evaluationEcoleService) {
        this.evaluationEcoleService = evaluationEcoleService;
    }

    @GetMapping
    public List<EvaluationEcole> getAll() {
        return evaluationEcoleService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public EvaluationEcole getById(@PathVariable Long id) {
        return evaluationEcoleService.getEvaluationById(id);
    }

    @PostMapping
    public EvaluationEcole create(@RequestBody EvaluationEcole evaluation) {
        return evaluationEcoleService.createEvaluation(evaluation);
    }

    @PutMapping("/{id}")
    public EvaluationEcole update(@PathVariable Long id, @RequestBody EvaluationEcole evaluation) {
        return evaluationEcoleService.updateEvaluation(id, evaluation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        evaluationEcoleService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
