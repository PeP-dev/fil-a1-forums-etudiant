package org.filmt.projetagile.user.service;

import org.filmt.projetagile.user.model.UserModel;

public interface UserService {
    UserModel getUserByUsername(String username);
}
