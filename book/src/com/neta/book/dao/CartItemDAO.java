package com.neta.book.dao;

import com.neta.book.pojo.CartItem;
import com.neta.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    // 新增购物车项
    void addCartItem(CartItem cartItem);

    // 修改购物车项
    void updateCartItem(CartItem cartItem);

    // 获取特定用户的购物车
    List<CartItem> getCartItemList(User user);

}
