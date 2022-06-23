package org.filmt.projetagile.auth.dao;

import java.util.Optional;

import org.filmt.projetagile.user.model.UserModel;

public interface AuthenticationDAO {
    Optional<UserModel> loadByUsername(String userName);

    void registerUser(UserModel model);
}
