package org.filmt.projetagile.roles.controller;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.exception.RoleNotFoundException;
import org.filmt.projetagile.roles.model.Role;
import org.filmt.projetagile.roles.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractRoleController<T> {

    RoleService<T> roleService;

    @GetMapping(value = "", params = "user_name")
    public List<T> getByAnyRole(@RequestParam(name = "user_name") final String userName) {
        return roleService.getByAnyRole(userName);
    }

    @GetMapping(value = "", params = {"user_name", "role"})
    public List<T> getByRole(@RequestParam(name = "user_name") final String userName, @RequestParam final Role role) {
        return roleService.getByRole(userName, role);
    }

    @GetMapping(value = "", params = {"user_name", "entityId"})
    public Role getRoleForEntity(@RequestParam(name = "user_name") final String userName, @RequestParam final String entityId) {
        return roleService.getRoleForEntity(userName, entityId).orElseThrow(()-> new RoleNotFoundException(String.format("No role was found for user '%s' at entity '%s'", userName, entityId)));
    }

    @PreAuthorize("schoolPrivilegesHigherThan(#groupId, 'OWNER')")
    @DeleteMapping(value = "", params = {"user_name", "entityId"})
    public void removeRole(@RequestParam(name = "user_name") final String userName, @RequestParam final String entityId) {
        roleService.removeRole(userName, entityId);
    }

    @PreAuthorize("schoolPrivilegesHigherThan(#groupId, 'OWNER')")
    @PostMapping(value = "", params = {"user_name", "entityId", "role"})
    public void changeRole(@RequestParam(name = "user_name") String userName, @RequestParam final String entityId, @RequestParam final Role role) {
        roleService.changeRole(userName, entityId, role);
    }
}
