package com.neta.qqzone.service;

import com.neta.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    // 登录
    UserBasic login(String loginId, String pwd);

    // 获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    // 根据 id 获取指定用户
    UserBasic getUserBasicById(Integer id);
}
