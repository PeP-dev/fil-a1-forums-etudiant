package org.filmt.projetagile.school.service;

import java.util.List;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.school.model.School;

public interface SchoolService {
    School getSchoolById(final String schoolId);

    List<Group> getSchoolByTypeId(final String typeId);

    School getSchoolByLibelle(final String libelle);

    School create(final School school);

    School update(final School school);

    void delete(final String id);

}
