package com.neta.book.pojo;

import java.util.Map;

public class Cart {
    private Map<Integer,CartItem> cartItemMap;  // key 是 book 的 id
    private Double totalMoney;   // 总金额
    private Integer totalCount;  // 总数

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if(cartItemMap != null && cartItemMap.size() > 0 ){
            for (Map.Entry<Integer, CartItem> entry : cartItemMap.entrySet()) {
                CartItem cartItem = entry.getValue();
                totalMoney += cartItem.getBuyCount() * cartItem.getBook().getPrice();
            }
        }
        return totalMoney;
    }


    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

}
