package org.filmt.projetagile.auth.service;

import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.LoginCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails login(String userName, String password);

    UserDetails loginWithToken(String token);

    String createToken(UserDetails credentials);

    GroupRole getRoleByUserNameAndGroupId(String userName, String groupId);

    UserDetails registerUser(LoginCredentials credentials);
}
