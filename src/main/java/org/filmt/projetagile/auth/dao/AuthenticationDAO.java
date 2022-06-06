package org.filmt.projetagile.auth.dao;

import java.util.Optional;

import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.UserModel;

public interface AuthenticationDAO {
    GroupRole getRoleByUserNameAndGroupId(String userName, String groupId);

    Optional<UserModel> loadByUsername(String userName);

    void registerUser(UserModel model);
}
