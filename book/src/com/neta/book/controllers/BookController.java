package com.neta.book.controllers;

import com.neta.book.pojo.Book;
import com.neta.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {

    private BookService bookService;


    public String index(HttpSession session){

        List<Book> bookList = bookService.getBookList();

        session.setAttribute("bookList",bookList);

        return "index";
    }

}
