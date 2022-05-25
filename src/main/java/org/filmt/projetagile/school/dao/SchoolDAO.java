package org.filmt.projetagile.school.dao;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.school.model.School;

import java.util.List;

public interface SchoolDAO {

    School getSchoolById(String schoolId);

    List<Group> getSchoolByTypeId(String typeId);

    School getSchoolByLibelle(String libelle);

    School create(School school);

    School update(School school);

    void delete(String id);

}
