package org.filmt.projetagile.roles.dao;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.roles.model.Role;

public interface RoleDAO<T> {

    List<T> getByAnyRole(String userId);

    List<T> getByRole(String userId, Role role);

    Optional<Role> getRoleForEntity(final String userId, String entityId);

    void addRole(String userId, final String entityId, Role role);

    void removeRole(String userId, final String entityId);

    void changeRole(String userId, final String entityId, Role role);
}
