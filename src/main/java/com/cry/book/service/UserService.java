package com.cry.book.service;

import com.cry.book.data.LoginRequset;
import com.cry.book.entity.User;

public interface UserService {

    User getAllUsers(LoginRequset req);

    void AddUser(User user);

    int checkUser(User user);
}