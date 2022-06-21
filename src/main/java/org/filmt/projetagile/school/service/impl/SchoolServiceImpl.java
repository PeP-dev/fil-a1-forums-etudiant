package org.filmt.projetagile.school.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.school.dao.SchoolDAO;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    SchoolDAO schoolDAO;

    @Override
    public Optional<School> getSchoolById(final String schoolId) {
        return schoolDAO.getSchoolById(schoolId);
    }

    @Override
    public List<School> getSchoolByTypeId(final String typeId) {
        return schoolDAO.getSchoolByTypeId(typeId);
    }

    @Override
    public List<School> getAll() {
        return schoolDAO.getAll();
    }

    @Override
    public School getSchoolByLibelle(final String libelle) {
        return schoolDAO.getSchoolByLibelle(libelle);
    }

    @Override
    public School create(final School school) {
        school.setId(UUID.randomUUID().toString());
        return schoolDAO.create(school);
    }

    @Override
    public School update(final School school) {
        verifySchoolPresentOrThrow(school.getId());
        return schoolDAO.update(school);
    }

    @Override
    public void delete(final String id) {
        verifySchoolPresentOrThrow(id);
        schoolDAO.delete(id);
    }

    private void verifySchoolPresentOrThrow(String id) {
        if (getSchoolById(id).isEmpty()) {
            throw SchoolNotFoundException.genericById(id);
        }
    }
}
