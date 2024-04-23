package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Etudiant;


import tn.enicarthage.EnicarthageUniverse.entities.Matieres;
import tn.enicarthage.EnicarthageUniverse.services.StatisticsService;

import java.util.List;
import java.util.Map;


@ RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/all-notes/{studentId}")
    public ResponseEntity<Map<Matieres, Float>> getAllNotes(@PathVariable Long studentId) {
        Map<Matieres, Float> notes = statisticsService.getAllNotes(new Etudiant(studentId));
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/average-scores")
    public ResponseEntity<Map<Matieres, Float>> getAverageScores() {
        List<Etudiant> students = statisticsService.getAllStudents();
        Map<Matieres, Float> averageScores = statisticsService.calculateAverageScores(students);
        return ResponseEntity.ok(averageScores);
    }

    @GetMapping("/top-students/total")
    public ResponseEntity<List<Etudiant>> getTopStudentsByTotalScore(@RequestParam(required = false, defaultValue = "3") int n) {
        List<Etudiant> students = statisticsService.getAllStudents();
        List<Etudiant> topStudents = statisticsService.findTopStudentsByTotalScore(students, n);
        return ResponseEntity.ok(topStudents);
    }



}
