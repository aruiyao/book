package com.cry.book.data;

import com.cry.book.entity.Book;
import com.cry.book.entity.User;

public class QueryReviewByCondReq {
    private Book book;

    private User user;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryReviewByCondReq [book=");
        builder.append(book);
        builder.append(", user=");
        builder.append(user);
        builder.append("]");
        return builder.toString();
    }

}
