package org.filmt.projetagile.replies.dao;

import org.filmt.projetagile.replies.model.Reply;

import java.util.List;

public interface ReplyDAO {

    Reply getReplyById(String replyId);

    void create(Reply reply);

    Reply update(Reply reply);

    void delete(String id);

    List<Reply> getRepliesByPostId(String postId);

}
