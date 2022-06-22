package org.filmt.projetagile.likes;

import org.filmt.projetagile.user.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Like<T> {
    T content;
    UserModel user;
}
