package com.neta.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // post 方式，设置编码，防止中文乱码
        // 必须在所有获取参数之前
        request.setCharacterEncoding("utf-8");

        // 获取参数
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fountStr);
        String remark = request.getParameter("remark");

        System.out.printf("fname=%s\nprice=%d\nfcount=%d\nremark=%s",fname,price,fcount,remark);
    }
}
