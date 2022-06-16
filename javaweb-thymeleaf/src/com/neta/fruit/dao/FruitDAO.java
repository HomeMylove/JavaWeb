package com.neta.fruit.dao;

import com.neta.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    // 获取所有库存信息
    List<Fruit> getFruitList();
}
