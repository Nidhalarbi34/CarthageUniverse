package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;
import tn.enicarthage.EnicarthageUniverse.repsitories.CoursRepository;
import tn.enicarthage.EnicarthageUniverse.services.CoursService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/courses")
public class CoursController {
    @Autowired
     CoursService coursService;
    @Autowired
    private CoursRepository coursRepository;


    @Autowired
    public CoursController(CoursService coursService, CoursRepository coursRepository) {
        this.coursService = coursService;
        this.coursRepository = coursRepository;
    }

    @RequestMapping(method = RequestMethod.GET )
//RequestBody:tekhdh vrb tabaathhom lel contrl kn sar c bon snn erreur
    public List<Cours> getAllCourses(){
        return coursService.getAllCourses();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCourseById(@PathVariable Long id) {
        Cours cours = coursService.getCourseById(id);
        if (cours != null) {
            return new ResponseEntity<>(cours, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<Cours> getCourseByTitre(@RequestParam String titre) {

        return coursService.getCourseByTitre(titre);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cours> updateCourse(@PathVariable Long id, @RequestBody Cours cours) {
        Cours updatedCourse = coursService.updateCourse(id, cours);
        if (updatedCourse != null) {
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCourse(@RequestBody Cours cours) {
        try {
            Cours savedCourse = coursRepository.save(cours);
            return ResponseEntity.ok(savedCourse);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create course");
        }
    }



}
