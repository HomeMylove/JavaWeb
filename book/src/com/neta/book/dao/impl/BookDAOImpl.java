package com.neta.book.dao.impl;

import com.neta.book.dao.BookDAO;
import com.neta.book.pojo.Book;
import com.neta.myssm.basedao.BaseDAO;


import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book");
    }
}
