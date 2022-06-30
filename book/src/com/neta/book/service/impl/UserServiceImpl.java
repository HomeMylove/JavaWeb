package com.neta.book.service.impl;

import com.neta.book.dao.UserDAO;
import com.neta.book.pojo.User;
import com.neta.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }
}
