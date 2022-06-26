package com.neta.qqzone.controller;

import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.TopicService;
import com.neta.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session){
        UserBasic userBasic = userBasicService.login(loginId,pwd);

        if(userBasic != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);


            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            // friend 表示是谁的空间
            // 如果和 userBasic 不同，就是别人的空间
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else {
            return "login";
        }
    }

    // 进入好友空间
    public String friend(Integer id, HttpSession session){
        // 获取指定用户信息
        UserBasic currFriend = userBasicService.getUserBasicById(id);

        List<Topic> topicList = topicService.getTopicList(currFriend);

        currFriend.setTopicList(topicList);

        session.setAttribute("friend",currFriend);

        return "index";
    }


}
