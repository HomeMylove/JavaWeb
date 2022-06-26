package com.neta.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue="+initValue);

        ServletContext context = getServletContext();
        String location = context.getInitParameter("contextConfigLocation");
        System.out.println("location="+location);


    }
}

// Servlet 声明周期
// 实例化
// 初始化
// 服务
// 销毁
