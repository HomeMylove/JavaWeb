package com.neta.qqzone.controller;

import com.neta.qqzone.pojo.Reply;
import com.neta.qqzone.pojo.Topic;
import com.neta.qqzone.pojo.UserBasic;
import com.neta.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class ReplyController {

    private ReplyService replyService;

    public String addReply(Integer topicId, String content, HttpSession session){

        Object userBasic = session.getAttribute("userBasic");

        if(userBasic != null){
            UserBasic author = (UserBasic) userBasic;
            Topic topic = new Topic(topicId);
            Reply reply = new Reply(content,LocalDateTime.now(),author,topic);
            replyService.addReply(reply);
        }

        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }


    public String delReply(Integer replyId,Integer topicId){
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

}
