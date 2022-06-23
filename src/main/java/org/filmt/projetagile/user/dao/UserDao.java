package org.filmt.projetagile.user.dao;

import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<UserModel> getUserByUsername(String username);

    List<UserModel> getUsers();

    UserModel update(UserModel user);

    void delete(String username);
}
