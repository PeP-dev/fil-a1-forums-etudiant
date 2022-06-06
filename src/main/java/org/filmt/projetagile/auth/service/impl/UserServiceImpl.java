package org.filmt.projetagile.auth.service.impl;

import org.filmt.projetagile.auth.dao.AuthenticationDAO;
import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl implements UserService {

    AuthenticationDAO authenticationDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return authenticationDAO.loadByUsername().orElseThrow(() -> new UsernameNotFoundException(String.format("Couldn't find user with matching username : '%s'", username)));
    }

    @Override
    public GroupRole getRoleByUserNameAndGroupId(final String userName, final String groupId) {
        return authenticationDAO.getRoleByUserNameAndGroupId(userName, groupId);
    }
}
