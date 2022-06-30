package com.neta.book.controllers;

import com.neta.book.pojo.Book;
import com.neta.book.pojo.CartItem;
import com.neta.book.pojo.User;
import com.neta.book.service.CartItemService;

import javax.servlet.http.HttpSession;

public class CartController {

    private CartItemService cartItemService;

    public String addCart(Integer bookId, HttpSession session){
        // 添加到购物车
        // 如果存在 就 + 1
        // 否则 新增 数量为 1
        User user =(User) session.getAttribute("currUser");

        CartItem cartItem = new CartItem(new Book(bookId), 1, user);


        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }


}
