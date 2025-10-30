package fr.efrei.altn72.asta.service;

import fr.efrei.altn72.asta.exception.MissionNotFoundException;
import fr.efrei.altn72.asta.model.Mission;
import fr.efrei.altn72.asta.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // ✅ Récupérer toutes les missions
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    // ✅ Récupérer une mission par ID
    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionNotFoundException(id));
    }

    // ✅ Créer une mission
    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    // ✅ Mettre à jour une mission existante
    public Mission updateMission(Long id, Mission updatedMission) {
        Mission existing = getMissionById(id);
        existing.setMotsCles(updatedMission.getMotsCles());
        existing.setMetierCible(updatedMission.getMetierCible());
        existing.setCommentaires(updatedMission.getCommentaires());
        existing.setApprenti(updatedMission.getApprenti());
        return missionRepository.save(existing);
    }

    // ✅ Supprimer une mission
    public void deleteMission(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new MissionNotFoundException("Impossible de supprimer : mission non trouvée avec l'id " + id);
        }
        missionRepository.deleteById(id);
    }
}
