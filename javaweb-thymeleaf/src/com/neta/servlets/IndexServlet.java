package com.neta.servlets;

import com.neta.fruit.dao.FruitDAO;
import com.neta.fruit.dao.impl.FruitDAOImpl;
import com.neta.fruit.pojo.Fruit;
import com.neta.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// 注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();
        // 保存到 session 作用域
        HttpSession session = req.getSession();
        System.out.println(fruitList.size());
        session.setAttribute("fruitList",fruitList);

        // 视图模板
        // thymeleaf 会对应到物理视图名称上
        // 逻辑视图名称 index
        // view-prefix + 逻辑视图名称 + view-suffix
        // => / index .html
        super.processTemplate("index",req,resp);
    }
}
