package com.neta.fruit.dao;

import com.neta.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    // 获取所有库存信息
    List<Fruit> getFruitList(Integer pageNo,String keyword);

    // 根据 fid 获取对应的库存信息
    Fruit getFruitById(Integer fid);

    // 修改
    void updateFruit(Fruit fruit);

    // 删除
    void delFruit(int fid);

    // 添加
    void addFruit(Fruit fruit);

    // 获取总条数
    int getFruitCount(String keyword);
}
