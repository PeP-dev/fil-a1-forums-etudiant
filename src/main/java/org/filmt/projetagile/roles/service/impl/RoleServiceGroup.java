package org.filmt.projetagile.roles.service.impl;

import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.roles.dao.RoleDAO;
import org.filmt.projetagile.roles.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceGroup extends RoleService<Group> {
    final GroupService groupService;

    public RoleServiceGroup(final RoleDAO<Group> roleDAO, final GroupService groupService) {
        super(roleDAO);
        this.groupService = groupService;
    }

    @Override
    public void throwIfMissingEntity(final String entityId) throws PublicException {
        if (groupService.getGroupById(entityId).isEmpty()) {
            throw GroupNotFoundException.genericById(entityId);
        }
    }
}
