package com.neta.qqzone.dao.impl;

import com.neta.myssm.basedao.BaseDAO;
import com.neta.qqzone.dao.UserBasicDAO;
import com.neta.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {

    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?",loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select * from t_user_basic where t_user_basic.id in (select fid from t_friend where uid = ?)";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return load("select * from t_user_basic where id = ?",id);
    }
}
