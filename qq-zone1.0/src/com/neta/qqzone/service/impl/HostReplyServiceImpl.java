package com.neta.qqzone.service.impl;

import com.neta.qqzone.dao.HostReplyDAO;
import com.neta.qqzone.pojo.HostReply;
import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;


    @Override
    public HostReply getHostReplyByReply(Reply reply) {
        return hostReplyDAO.getHostReplyByReply(reply);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
