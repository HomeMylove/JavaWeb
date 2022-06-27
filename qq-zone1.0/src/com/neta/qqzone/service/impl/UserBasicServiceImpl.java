package com.neta.qqzone.service.impl;

import com.neta.qqzone.dao.UserBasicDAO;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.TopicService;
import com.neta.qqzone.service.UserBasicService;

import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO ;


    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        return userBasicDAO.getUserBasicList(userBasic);
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }
}
