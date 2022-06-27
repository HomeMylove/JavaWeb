package com.neta.qqzone.service;

import com.neta.qqzone.pojo.HostReply;
import com.neta.qqzone.pojo.Reply;

public interface HostReplyService {

     HostReply getHostReplyByReply(Reply reply);

    void delHostReply(Integer id);

}
