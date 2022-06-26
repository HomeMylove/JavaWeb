package com.neta.myssm.filters.dao.impl;

import com.neta.myssm.filters.dao.FruitDAO;
import com.neta.fruit.pojo.Fruit;
import com.neta.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    public FruitDAOImpl() throws ClassNotFoundException {
    }

    @Override
    public List<Fruit> getFruitList(Integer pageNo,String keyword)  {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? , 5","%"+keyword+"%", "%"+keyword+"%",(pageNo - 1) * 5);
    }

    @Override
    public Fruit getFruitById(Integer fid)  {
        return super.load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?, price=?, fcount=?,remark=? where fid=?";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(int fid) {
        String sql = "delete from t_fruit where fid = ?";
        super.executeUpdate(sql, fid);
    }

    @Override
    public void addFruit(Fruit fruit){
        String sql = "insert into t_fruit (fname,price,fcount,remark) values (?,?,?,?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    public int getFruitCount(String keyword) {
        return ((Long) super.executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ?","%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }
}
