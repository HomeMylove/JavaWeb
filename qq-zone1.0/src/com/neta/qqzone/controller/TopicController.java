package com.neta.qqzone.controller;


import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {
    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session){

        Topic topic = topicService.getTopicById(id);


        session.setAttribute("topic",topic);

        return "frames/detail";
    }

    public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session){
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }

}
