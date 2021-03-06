package com.neta.qqzone.dao.impl;

import com.neta.myssm.basedao.BaseDAO;
import com.neta.qqzone.dao.TopicDAO;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return executeQuery("select * from t_topic where author = ?",userBasic.getId());
    }

    @Override
    public Topic getTopicById(Integer id) {
        return load("select * from t_topic where id = ?",id);
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("delete from t_topic where id = ?",topic.getId());
    }


}
