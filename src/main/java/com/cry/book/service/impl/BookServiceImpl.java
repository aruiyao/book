package com.cry.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cry.book.entity.Book;
import com.cry.book.mapper.BookMapper;
import com.cry.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book); 
    }

    @Override
    public List < Book > queryBookByCond(Book book) {
        return bookMapper.queryBookByCond(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }
}
