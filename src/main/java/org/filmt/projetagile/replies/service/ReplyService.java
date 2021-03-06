package org.filmt.projetagile.replies.service;

import org.filmt.projetagile.replies.model.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyService {

    Optional<Reply> getReplyById(String replyId);

    Reply create(Reply reply);

    Reply update(Reply reply);

    void delete(String id);

    List<Reply> getRepliesByPostId(String postId);

    List<Reply> getCommentsByReplyId(String replyId);


}
