package com.neta.book.service.impl;

import com.neta.book.dao.BookDAO;
import com.neta.book.pojo.Book;
import com.neta.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }
}
