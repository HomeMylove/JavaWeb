package com.neta.book.dao.impl;

import com.neta.book.dao.UserDAO;
import com.neta.book.pojo.User;
import com.neta.myssm.basedao.BaseDAO;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    // 获取用户
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
    }
}
