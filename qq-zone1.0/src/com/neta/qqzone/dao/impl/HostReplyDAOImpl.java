package com.neta.qqzone.dao.impl;

import com.neta.myssm.basedao.BaseDAO;
import com.neta.qqzone.dao.HostReplyDAO;
import com.neta.qqzone.pojo.HostReply;
import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReply(Reply reply) {
        return load("select * from t_host_reply where reply = ?",reply.getId());
    }

    @Override
    public void delHostReply(Integer id) {
         executeUpdate("delete from t_host_reply where id = ?", id);
    }
}
