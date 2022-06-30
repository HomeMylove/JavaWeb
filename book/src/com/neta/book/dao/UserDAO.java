package com.neta.book.dao;

import com.neta.book.pojo.User;

public interface UserDAO {


    User getUser(String uname,String pwd);


}
