package org.filmt.projetagile.school.service;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.school.model.School;

public interface SchoolService {
    Optional<School> getSchoolById(final String schoolId);

    List<School> getSchoolByTypeId(final String typeId);

    List<School> getAll();

    School getSchoolByLibelle(final String libelle);

    School create(final School school);

    School update(final School school);

    void delete(final String id);

}
