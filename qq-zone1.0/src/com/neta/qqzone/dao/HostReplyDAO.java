package com.neta.qqzone.dao;

import com.neta.qqzone.pojo.HostReply;
import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;

public interface HostReplyDAO {

     HostReply getHostReplyByReply(Reply reply);

    void delHostReply(Integer id);

}
