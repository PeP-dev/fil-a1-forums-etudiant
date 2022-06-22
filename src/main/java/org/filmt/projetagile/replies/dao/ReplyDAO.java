package org.filmt.projetagile.replies.dao;

import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.replies.model.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyDAO {

    Optional<Reply> getReplyById(String replyId);

    void create(Reply reply);

    Reply update(Reply reply);

    void delete(String id);

    List<Reply> getRepliesByPostId(String postId);

    List<Reply> getCommentsByReplyId(String replyId);

}
