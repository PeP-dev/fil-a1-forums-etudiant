package org.filmt.projetagile.school.dao;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.school.model.School;

public interface SchoolDAO {

    Optional<School> getSchoolById(String schoolId);

    List<School> getSchoolByTypeId(String typeId);

    List<School> getAll();

    School getSchoolByLibelle(String libelle);

    School create(School school);

    School update(School school);

    void delete(String id);

}
