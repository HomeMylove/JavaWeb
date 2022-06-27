package com.neta.qqzone.service.impl;

import com.neta.qqzone.dao.ReplyDAO;
import com.neta.qqzone.pojo.HostReply;
import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.HostReplyService;
import com.neta.qqzone.service.ReplyService;
import com.neta.qqzone.service.UserBasicService;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;

    private HostReplyService hostReplyService;

    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(topicId);
        for (Reply reply : replyList) {
            HostReply hostReply = hostReplyService.getHostReplyByReply(reply);
            reply.setHostReply(hostReply);
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        // 1. 获取 reply
        Reply reply = replyDAO.getReplyById(id);
        // 2. 如果有关联的 host reply ，先删除
        HostReply hostReply = hostReplyService.getHostReplyByReply(reply);

        if(hostReply != null){
            hostReplyService.delHostReply(hostReply.getId());
        }

        // 3. 删除 reply
        replyDAO.delReply(id);
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic.getId());
        if(replyList != null){
            for (Reply reply : replyList) {
                delReply(reply.getId());
            }
        }
    }
}
