package org.filmt.projetagile.school.controller;

import java.util.List;

import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import static org.filmt.projetagile.school.controller.SchoolController.SCHOOL_ENDPOINT_URI;

@RestController
@RequestMapping(SCHOOL_ENDPOINT_URI)
@AllArgsConstructor
public class SchoolController {
    public static final String SCHOOL_ENDPOINT_URI = "/api/schools";

    public static final String GET_BY_ID = "/{schoolId}";
    SchoolService schoolService;

    @GetMapping(GET_BY_ID)
    public ResponseEntity<School> getSchoolById(@PathVariable final String schoolId) {
        return ResponseEntity.ok(
            schoolService.getSchoolById(schoolId)
                .orElseThrow(()->SchoolNotFoundException.genericById(schoolId)));
    }

    @GetMapping(value = "", params = "typeId")
    public ResponseEntity<List<School>> getSchoolByTypeId(@RequestParam final String typeId) {
        return ResponseEntity.ok(schoolService.getSchoolByTypeId(typeId));
    }
    @GetMapping(value = "", params = "libelle")
    public ResponseEntity<School> getSchoolByLibelle(@RequestParam final String libelle) {
        return ResponseEntity.ok(schoolService.getSchoolByLibelle(libelle));
    }

    @GetMapping("")
    public ResponseEntity<List<School>> getAll() {
        return ResponseEntity.ok(schoolService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<School> create(@RequestBody final School school){
        School created = schoolService.create(school);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<School> update(@RequestBody final School school) {
        return ResponseEntity.ok(schoolService.update(school));
    }

    @DeleteMapping("/{schoolId}")
    public ResponseEntity<Void> delete(@PathVariable final String schoolId) {
        schoolService.delete(schoolId);
        return ResponseEntity.noContent().build();
    }
}
