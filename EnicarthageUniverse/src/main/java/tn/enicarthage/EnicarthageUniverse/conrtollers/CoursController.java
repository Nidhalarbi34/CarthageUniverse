package tn.enicarthage.EnicarthageUniverse.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.EnicarthageUniverse.entities.Cours;
import tn.enicarthage.EnicarthageUniverse.services.CoursService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursController {

    private final CoursService coursService;

    @Autowired
    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping
    public ResponseEntity<List<Cours>> getAllCourses() {
        List<Cours> courses = coursService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
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

    @PostMapping
    public ResponseEntity<Cours> createCourse(@RequestBody Cours cours) {
        Cours createdCourse = coursService.createCourse(cours);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
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
}
