package com.neta.book.service;

import com.neta.book.pojo.Cart;
import com.neta.book.pojo.CartItem;
import com.neta.book.pojo.User;

public interface CartItemService {

    void addCartItem(CartItem cartItem);


    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    // 加载指定用户的购物车信息
    Cart getCart(User user);

}
