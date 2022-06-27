package com.neta.qqzone.dao;

import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyDAO {
    // 获取指定日志的回复列表
    List<Reply> getReplyList(Integer id);

    // 添加回复
    void addReply(Reply reply);

    // 删除回复
    void delReply(Integer id);

    // 根据 id 获取指定 reply
    Reply getReplyById(Integer id);
}
