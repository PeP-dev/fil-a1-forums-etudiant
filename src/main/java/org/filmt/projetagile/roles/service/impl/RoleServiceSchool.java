package org.filmt.projetagile.roles.service.impl;

import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.roles.dao.RoleDAO;
import org.filmt.projetagile.roles.service.RoleService;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceSchool extends RoleService<School> {
    final SchoolService schoolService;

    public RoleServiceSchool(final RoleDAO<School> roleDAO, final SchoolService schoolService) {
        super(roleDAO);
        this.schoolService = schoolService;
    }

    @Override
    public void throwIfMissingEntity(final String entityId) throws PublicException {
        if (schoolService.getSchoolById(entityId).isEmpty()) {
            throw SchoolNotFoundException.genericById(entityId);
        }
    }
}
