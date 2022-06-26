package com.neta.fruit.controllers;

import com.neta.fruit.dao.FruitDAO;
import com.neta.fruit.dao.impl.FruitDAOImpl;
import com.neta.fruit.pojo.Fruit;
import com.neta.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class FruitController {

    private final FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest req) {
        if (pageNo == null) pageNo = 1;

        HttpSession session = req.getSession();

        if (oper != null && oper.equals("search")) {
            if (StringUtil.isEmpty(keyword))
                keyword = "";
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        List<Fruit> fruitList = fruitDAO.getFruitList(pageNo, keyword);

        session.setAttribute("fruitList", fruitList);
        session.setAttribute("pageNo", pageNo);

        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

    private String del(Integer fid) {
        if (fid != null) {
            fruitDAO.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitDAO.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }
}
