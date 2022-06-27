package com.neta.qqzone.dao.impl;

import com.neta.myssm.basedao.BaseDAO;
import com.neta.qqzone.dao.ReplyDAO;
import com.neta.qqzone.pojo.Reply;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Integer id) {
        return executeQuery("select * from t_reply where topic = ?", id);
    }

    @Override
    public void addReply(Reply reply) {
        String sql = "insert into t_reply (content,replyDate,author,topic) values (?,?,?,?)";
        executeUpdate(sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?",id);
    }

    @Override
    public Reply getReplyById(Integer id) {
        return load("select * from t_reply where id = ?",id);
    }
}
