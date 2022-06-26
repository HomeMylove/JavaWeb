package com.neta.qqzone.service;

import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    // 获取特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);
}
