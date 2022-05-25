package org.filmt.projetagile.likes.service;

import java.util.List;

import org.filmt.projetagile.likes.dao.LikeDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class LikeService<T> {
    LikeDAO<T> likeDao;

    List<T> getLikedContentByUser(String userId) {
        return likeDao.getLikedContentByUser(userId);
    }

    int getLikeAmount(String contentId) {
        return likeDao.getLikeAmount(contentId);
    }

    void removeLike(String userId, String contentId) {
        likeDao.removeLike(userId, contentId);
    }
}
