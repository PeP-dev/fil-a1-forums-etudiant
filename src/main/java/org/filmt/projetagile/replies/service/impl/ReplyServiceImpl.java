package org.filmt.projetagile.replies.service.impl;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.exception.PostNotFoundException;
import org.filmt.projetagile.exception.ReplyNotFoundException;
import org.filmt.projetagile.replies.dao.ReplyDAO;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.ReplyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDao ;

    @Override
    public Reply getReplyById(String replyId) {
        return replyDao.getReplyById(replyId);
    }

    @Override
    public Reply create(Reply reply) {
        reply.setId(UUID.randomUUID().toString());
        reply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        //if (reply.getPostId().isEmpty()) reply.setReplyId(null);
        replyDao.create(reply);
        return reply ;
    }

    @Override
    public Reply update(Reply reply) {
        return replyDao.update(reply);
    }

    @Override
    public void delete(String id) {
        replyDao.delete(id);
    }

    @Override
    public List<Reply> getRepliesByPostId(String postId) {
        if (replyDao.getRepliesByPostId(postId).isEmpty()) {
            throw PostNotFoundException.genericById(postId);
        }
        return replyDao.getRepliesByPostId(postId);
    }

    @Override
    public List<Reply> getCommentsByReplyId(String replyId) {
        if (replyDao.getCommentsByReplyId(replyId).isEmpty()) {
            throw ReplyNotFoundException.genericById(replyId);
        }
        return replyDao.getCommentsByReplyId(replyId);
    }


}
