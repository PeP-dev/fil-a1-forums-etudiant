package org.filmt.projetagile.auth.service.impl;

import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.UserModel;
import org.filmt.projetagile.auth.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceMock implements UserService {

    @Override
    public GroupRole getRoleByUserNameAndGroupId(final String userName, final String groupId) {
        return GroupRole.ADMIN;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return new UserModel(username, new BCryptPasswordEncoder().encode("pwd"));
    }
}
