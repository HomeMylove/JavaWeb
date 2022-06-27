package com.neta.qqzone.service.impl;

import com.neta.qqzone.dao.TopicDAO;
import com.neta.qqzone.dao.UserBasicDAO;
import com.neta.qqzone.dao.impl.UserBasicDAOImpl;
import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.ReplyService;
import com.neta.qqzone.service.TopicService;
import com.neta.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    private UserBasicService userBasicService;

    private ReplyService replyService;


    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    public Topic getTopicById(Integer id){
        Topic topic = topicDAO.getTopicById(id);
        UserBasic author = userBasicService.getUserBasicById(topic.getAuthor().getId());
        topic.setAuthor(author);

        List<Reply> replyList = replyService.getReplyListByTopicId(id);
        topic.setReplyList(replyList);

        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopicById(id);

        if(topic != null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }
}
