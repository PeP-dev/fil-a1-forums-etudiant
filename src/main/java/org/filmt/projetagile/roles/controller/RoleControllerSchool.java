package org.filmt.projetagile.roles.controller;

import org.filmt.projetagile.roles.service.RoleService;
import org.filmt.projetagile.school.model.School;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles/schools")
public class RoleControllerSchool extends AbstractRoleController<School> {

    public RoleControllerSchool(final RoleService<School> roleService) {
        super(roleService);
    }
}
