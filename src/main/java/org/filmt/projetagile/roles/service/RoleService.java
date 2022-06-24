package org.filmt.projetagile.roles.service;

import java.util.List;
import java.util.Optional;


import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.exception.RoleNotFoundException;
import org.filmt.projetagile.roles.dao.RoleDAO;
import org.filmt.projetagile.roles.model.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RoleService<T> {
    RoleDAO<T> roleDAO;

    public List<T> getByAnyRole(final String userName) {
        throwIfMissingUser(userName);
        return roleDAO.getByAnyRole(userName);
    }

    public List<T> getByRole(final String userName, final Role role) {
        throwIfMissingUser(userName);
        return roleDAO.getByRole(userName, role);
    }

    public Optional<Role> getRoleForEntity(final String userName, final String entityId) throws RoleNotFoundException {
        throwIfMissingUser(userName);
        throwIfMissingEntity(entityId);

        return roleDAO.getRoleForEntity(userName, entityId);
    }

    public void removeRole(final String userName, final String entityId) {
        throwIfMissingUser(userName);
        throwIfMissingEntity(entityId);

        roleDAO.removeRole(userName, entityId);
    }

    public void changeRole(final String userName, final String entityId, final Role role) {
        throwIfMissingUser(userName);
        throwIfMissingEntity(entityId);
        if(getRoleForEntity(userName, entityId).isPresent()) {
            roleDAO.changeRole(userName, entityId, role);
        } else {
            roleDAO.addRole(userName, entityId, role);
        }
    }

    private void throwIfMissingUser(String userName) throws PublicException {
    }

    protected abstract void throwIfMissingEntity(String entityId) throws PublicException;
}
