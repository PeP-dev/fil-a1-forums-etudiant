package org.filmt.projetagile.school.controller;

import java.util.List;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/schools")
public class SchoolController {
    SchoolService schoolService;

    @GetMapping("/{schoolId}")
    public School getSchoolById(@PathVariable final String schoolId) {
        return schoolService.getSchoolById(schoolId);
    }

    @GetMapping(value = "/", params = "typeId")
    public List<Group> getSchoolByTypeId(@RequestParam final String typeId) {
        return schoolService.getSchoolByTypeId(typeId);
    }
    @GetMapping(value = "/", params = "libelle")
    public School getSchoolByLibelle(final String libelle) {
        return schoolService.getSchoolByLibelle(libelle);
    }

    @PostMapping("/")
    public School create(final School school) {
        return schoolService.create(school);
    }

    @PutMapping("/")
    public School update(final School school) {
        return schoolService.update(school);
    }

    @DeleteMapping("/{schoolId}")
    public void delete(@PathVariable  final String schoolId) {
        schoolService.delete(schoolId);
    }
}
