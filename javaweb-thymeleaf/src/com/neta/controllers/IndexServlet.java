package com.neta.controllers;

import com.neta.fruit.dao.FruitDAO;
import com.neta.fruit.dao.impl.FruitDAOImpl;
import com.neta.fruit.pojo.Fruit;
import com.neta.myssm.myspringmvc.ViewBaseServlet;
import com.neta.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// 注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取关键字
        req.setCharacterEncoding("utf-8");

        String keyword = null;
        String oper = req.getParameter("oper");
        int pageNo = 1;
        HttpSession session = req.getSession();

        if (oper != null && oper.equals("search")) {
            keyword = req.getParameter("keyword");
            if(StringUtil.isEmpty(keyword))
                keyword = "";
            session.setAttribute("keyword",keyword);
        }else {
            String pageNoStr = req.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr))
                pageNo = Integer.parseInt(pageNoStr);
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }





        FruitDAO fruitDAO = new FruitDAOImpl();



        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo,keyword);
        // 保存到 session 作用域

//        System.out.println(fruitList.size());
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageNo",pageNo);

        // 总页数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 -1) / 5;
        session.setAttribute("pageCount",pageCount);

        // 视图模板
        // thymeleaf 会对应到物理视图名称上
        // 逻辑视图名称 index
        // view-prefix + 逻辑视图名称 + view-suffix
        // => / index .html
        super.processTemplate("index",req,resp);
    }
}
