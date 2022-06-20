package org.filmt.projetagile.user.dao;

import org.filmt.projetagile.user.model.UserModel;

public interface UserDao {
    UserModel getUserByUsername(String username);
}
