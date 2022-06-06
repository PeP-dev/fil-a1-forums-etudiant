package org.filmt.projetagile.auth.service;

import org.filmt.projetagile.auth.model.GroupRole;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    GroupRole getRoleByUserNameAndGroupId(String userName, String groupId);
}
