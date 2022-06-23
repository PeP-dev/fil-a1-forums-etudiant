package org.filmt.projetagile.user.service.impl;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.exception.PostNotFoundException;
import org.filmt.projetagile.exception.UserNotFoundException;
import org.filmt.projetagile.user.dao.UserDao;
import org.filmt.projetagile.user.model.UserModel;
import org.filmt.projetagile.user.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService   {

    private final UserDao userDao ;

    @Override
    public Optional<UserModel> getUserByUsername(String username) {

        if (userDao.getUserByUsername(username).isEmpty()){

            throw new UserNotFoundException("Cannot find user") ;
        }

        return userDao.getUserByUsername(username);
    }

    @Override
    public List<UserModel> getUsers() {
        if (userDao.getUsers().isEmpty()){
            throw new UserNotFoundException("Cannot find users") ;
        }
        return userDao.getUsers();
    }

    @Override
    public UserModel update(UserModel userModel) {
        if (userDao.getUserByUsername(userModel.getUsername()).isEmpty()){
            throw new UserNotFoundException("Cannot find user") ;
        }
        return userDao.update(userModel);
    }

    @Override
    public void delete(String id) {
        if (userDao.getUserByUsername(id).isEmpty()){
            throw new UserNotFoundException("Cannot find user") ;
        }
        userDao.delete(id);
    }


}
