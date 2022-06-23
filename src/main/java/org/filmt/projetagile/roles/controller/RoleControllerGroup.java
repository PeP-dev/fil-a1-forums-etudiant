package org.filmt.projetagile.roles.controller;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.roles.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles/groups")
public class RoleControllerGroup extends AbstractRoleController<Group> {

    public RoleControllerGroup(final RoleService<Group> groupService) {
        super(groupService);
    }
}
