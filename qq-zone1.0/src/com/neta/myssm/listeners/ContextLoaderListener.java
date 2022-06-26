package com.neta.myssm.listeners;

import com.neta.myssm.ioc.BeanFactory;
import com.neta.myssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        String path = servletContext.getInitParameter("contextConfigLocation");

        // 在上下文创建的时候创建 beanFactory 容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);


        servletContext.setAttribute("beanFactory",beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听到了销毁...");
    }
}
