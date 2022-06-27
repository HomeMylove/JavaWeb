package com.neta.qqzone.dao;

import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    // 获取指定用户的所有日志
    public List<Topic> getTopicList(UserBasic userBasic);

    public Topic getTopicById(Integer id);

    // 添加日志
    public void addTopic(Topic topic);

    // 删除日志
    public void delTopic(Topic topic);

    // 获取特定日志信息
}
