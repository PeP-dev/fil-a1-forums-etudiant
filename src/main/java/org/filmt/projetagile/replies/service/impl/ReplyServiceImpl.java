package org.filmt.projetagile.replies.service.impl;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.exception.PostNotFoundException;
import org.filmt.projetagile.exception.ReplyNotFoundException;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.replies.dao.ReplyDAO;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.ReplyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDao ;

    PostService postService ;

    @Override
    public Optional<Reply> getReplyById(String replyId) {
        return replyDao.getReplyById(replyId);
    }

    @Override
    public Reply create(Reply reply) {
        reply.setId(UUID.randomUUID().toString());
        reply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        replyDao.create(reply);
        return reply ;
    }

    @Override
    public Reply update(Reply reply) {
        if(postService.getPostById(reply.getPostId()).isEmpty()) {
            throw PostNotFoundException.genericById(reply.getPostId());
        }
        if (getReplyById(reply.getReplyId()).isEmpty()) {
            throw ReplyNotFoundException.genericById(reply.getReplyId());
        }
        if (getReplyById(reply.getId()).isEmpty()) {
            throw ReplyNotFoundException.genericById(reply.getId());
        }
        return replyDao.update(reply);
    }

    @Override
    public void delete(String id) {
        if(getReplyById(id).isPresent()) {
            replyDao.delete(id);
        } else {
            throw GroupNotFoundException.genericById(id);
        }
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
