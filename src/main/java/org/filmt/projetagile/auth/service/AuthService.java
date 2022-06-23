package org.filmt.projetagile.auth.service;

import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.RegisterCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    UserDetails login(String userName, String password);

    UserDetails loginWithToken(String token);

    String createToken(UserDetails credentials);

    GroupRole getRoleByUserNameAndGroupId(String userName, String groupId);

    UserDetails registerUser(RegisterCredentials credentials);
}
