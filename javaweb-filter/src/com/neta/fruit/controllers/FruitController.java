package com.neta.fruit.controllers;

import com.neta.fruit.pojo.Fruit;
import com.neta.fruit.service.FruitService;
import com.neta.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class FruitController {

    private FruitService fruitService = null;

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

        List<Fruit> fruitList = fruitService.getFruitList(pageNo, keyword);

        session.setAttribute("fruitList", fruitList);
        session.setAttribute("pageNo", pageNo);

        int pageCount = fruitService.getPageCount(keyword);

        session.setAttribute("pageCount", pageCount);

        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        fruitService.addFruit(new Fruit(0, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }

    private String del(Integer fid) {
        if (fid != null) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitService.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }


}
