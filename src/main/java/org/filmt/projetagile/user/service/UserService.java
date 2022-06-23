package org.filmt.projetagile.user.service;

import org.filmt.projetagile.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserModel> getUserByUsername(String username);

    List<UserModel> getUsers();

    UserModel update(final UserModel userModel);

    void delete(final String id);


}
