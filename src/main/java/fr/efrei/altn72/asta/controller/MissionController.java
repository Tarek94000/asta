package fr.efrei.altn72.asta.controller;

import fr.efrei.altn72.asta.model.Mission;
import fr.efrei.altn72.asta.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<Mission> getAll() {
        return missionService.getAllMissions();
    }

    @GetMapping("/{id}")
    public Mission getById(@PathVariable Long id) {
        return missionService.getMissionById(id);
    }

    @PostMapping
    public Mission create(@RequestBody Mission mission) {
        return missionService.createMission(mission);
    }

    @PutMapping("/{id}")
    public Mission update(@PathVariable Long id, @RequestBody Mission mission) {
        return missionService.updateMission(id, mission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
}
