package org.filmt.projetagile.school.service;

import java.util.Optional;

import org.filmt.projetagile.school.model.School;

public interface SchoolService {
    Optional<School> getSchoolById(String id);
}
