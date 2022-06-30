package com.neta.book.dao.impl;

import com.neta.book.dao.CartItemDAO;
import com.neta.book.pojo.CartItem;
import com.neta.book.pojo.User;
import com.neta.myssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        executeUpdate("insert into t_cart_item values (0,?,?,?)",cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean());
        System.out.println("add============\nbook="+cartItem.getBook().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        executeUpdate("update t_cart_item set buyCount = ? where id = ?",cartItem.getBuyCount(),cartItem.getId());
        System.out.println("update============\nbook="+cartItem.getBook().getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return executeQuery("select * from t_cart_item where userBean = ?",user.getId());
    }
}
