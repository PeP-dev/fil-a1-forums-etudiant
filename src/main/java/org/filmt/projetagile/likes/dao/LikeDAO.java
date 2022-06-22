package org.filmt.projetagile.likes.dao;

import java.util.List;

import org.filmt.projetagile.user.model.UserModel;

public interface LikeDAO<T> {
    List<T> getLikedContentByUser(String userId);

    int getContentLikeAmount(String contentId);
    void removeLike(String userId, String contentId);

    void addLike(String userId, String contentId);

    List<UserModel> getContentLike(String contentId);
}
