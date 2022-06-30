package com.neta.book.service.impl;

import com.neta.book.dao.CartItemDAO;
import com.neta.book.pojo.Cart;
import com.neta.book.pojo.CartItem;
import com.neta.book.pojo.User;
import com.neta.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {

    private CartItemDAO cartItemDAO;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        // 判断是否有
        if(cart != null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap == null){
                cartItemMap = new HashMap<>();
            }

            if(cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem item = cartItemMap.get(cartItem.getBook().getId());
                item.setBuyCount(item.getBuyCount()+1);
                updateCartItem(item);
            }else {
                addCartItem(cartItem);
            }
        }
        else {
            addCartItem(cartItem);
        }

    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        Map<Integer,CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }

        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }
}
