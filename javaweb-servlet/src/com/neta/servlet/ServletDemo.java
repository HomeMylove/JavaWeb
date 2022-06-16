package com.neta.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 演示 session
 */
public class ServletDemo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 session
        HttpSession session = req.getSession();
        System.out.println("Session id = "+session);
//        session.invalidate(); // 让会话立即失效
    }
}
