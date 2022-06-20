package org.filmt.projetagile.replies.service.impl;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.replies.dao.ReplyDAO;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.ReplyService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDAO replyDao ;

    @Override
    public Reply getReplyById(String replyId) {
        return null;
    }

    @Override
    public Reply create(Reply reply) {
        UUID id = UUID.randomUUID();
        reply.setId(id.toString());
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
    public List<Reply> getRepliesByPostId(String replyId) {
        return null;
    }
}
