package org.filmt.projetagile.likes.service;

import java.util.List;

import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.user.model.UserModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class LikeService<T> {

    LikeDAO<T> likeDao;

    public List<T> getLikedContentByUser(String userId) {
        return likeDao.getLikedContentByUser(userId);
    }

    public int getLikeAmount(String contentId) {
        throwIfMissingContent(contentId);
        return likeDao.getContentLikeAmount(contentId);
    }
    public List<UserModel> getContentLikes(String contentId) {
        throwIfMissingContent(contentId);
        return likeDao.getContentLike(contentId);
    }

    public void removeLike(String userId, String contentId) {
        throwIfMissingContent(contentId);
        throwIfMissingUser(userId);
        likeDao.removeLike(userId, contentId);
    }

    public void addLike(String userId, String contentId) {
        throwIfMissingContent(contentId);
        throwIfMissingUser(userId);
        likeDao.addLike(userId, contentId);
    }

    protected abstract void throwIfMissingContent(String contentId) throws PublicException;

    private void throwIfMissingUser(String userId) throws PublicException {
    }
}
