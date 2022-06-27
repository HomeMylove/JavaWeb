package com.neta.qqzone.service;

import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    // 根据 topic id 获取所有关联回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    // 添加 reply
   void addReply(Reply reply);

    // 删除 reply
    void delReply(Integer id);

    // 删除指定日志的所有回复
    void delReplyList(Topic topic);
}
