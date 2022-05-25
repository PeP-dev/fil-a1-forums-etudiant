package org.filmt.projetagile.likes.dao;

import java.util.Collections;
import java.util.List;

public interface LikeDAO<T> {


    default List<T> getLikedContentByUser(String userId) {
        return Collections.emptyList();
    }

    default int getLikeAmount(String contentId) {
        return 0;
    }

    default void removeLike(String userId, String contentId) {

    }
    
    String getDaoTableName();
}
