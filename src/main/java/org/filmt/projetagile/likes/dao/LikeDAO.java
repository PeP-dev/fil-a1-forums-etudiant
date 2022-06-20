package org.filmt.projetagile.likes.dao;

import java.util.List;

public interface LikeDAO<T> {
    List<T> getLikedContentByUser(String userId);

    int getLikeAmount(String contentId);
    void removeLike(String userId, String contentId);
}
