package org.filmt.projetagile.school.service.impl;

import java.util.List;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.school.dao.SchoolDAO;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.school.service.SchoolService;

public class SchoolServiceImpl implements SchoolService {

    SchoolDAO schoolDAO;

    @Override
    public School getSchoolById(final String schoolId) {
        return schoolDAO.getSchoolById(schoolId);
    }

    @Override
    public List<Group> getSchoolByTypeId(final String typeId) {
        return schoolDAO.getSchoolByTypeId(typeId);
    }

    @Override
    public School getSchoolByLibelle(final String libelle) {
        return schoolDAO.getSchoolByLibelle(libelle);
    }

    @Override
    public School create(final School school) {
        return schoolDAO.create(school);
    }

    @Override
    public School update(final School school) {
        return schoolDAO.update(school);
    }

    @Override
    public void delete(final String id) {
        schoolDAO.delete(id);
    }
}
