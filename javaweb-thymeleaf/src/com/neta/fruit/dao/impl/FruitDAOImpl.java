package com.neta.fruit.dao.impl;

import com.neta.fruit.dao.FruitDAO;
import com.neta.fruit.pojo.Fruit;
import com.neta.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
