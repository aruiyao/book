package com.cry.book.service;

import java.util.List;

import com.cry.book.entity.Book;

public interface BookService {
    void addBook(Book book);

    List < Book > queryBookByCond(Book book);
    
    void updateBook(Book book);
    
    void deleteBook(Integer id);
}
