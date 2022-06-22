package org.filmt.projetagile.likes.dao;

import java.util.List;

import org.filmt.projetagile.likes.Like;

public interface LikeDAO<T> {
    List<Like<T>> getLikedContentByUser(String userId);

    int getLikeAmount(String contentId);
    void removeLike(String userId, String contentId);

    void addLike(String userId, String contentId);
}
