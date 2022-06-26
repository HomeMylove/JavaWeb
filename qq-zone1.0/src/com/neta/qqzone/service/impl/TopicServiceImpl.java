package com.neta.qqzone.service.impl;

import com.neta.qqzone.dao.TopicDAO;
import com.neta.qqzone.dao.UserBasicDAO;
import com.neta.qqzone.dao.impl.UserBasicDAOImpl;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;


    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
