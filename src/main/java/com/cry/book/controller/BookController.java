package com.cry.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cry.book.data.QueryBookListResp;
import com.cry.book.entity.Book;
import com.cry.book.service.BookService;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/addBook")
    @ResponseBody
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping(value = "/queryBookByCond")
    @ResponseBody
    public QueryBookListResp queryBookByCond(Book book) {
        QueryBookListResp resp = new QueryBookListResp();
        List < Book > bookList = bookService.queryBookByCond(book);
        resp.setBookList(bookList);
        return resp;
    }

    @PostMapping(value = "/updateBook")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping(value = "/deleteBook")
    @ResponseBody
    public void deleteBook(@RequestBody Integer id) {
        bookService.deleteBook(id);
    }
}
