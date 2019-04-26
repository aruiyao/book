package com.cry.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cry.book.data.LoginRequset;
import com.cry.book.entity.User;
import com.cry.book.mapper.UserMapper;
import com.cry.book.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getAllUsers(LoginRequset req) {
        return userMapper.getAllUsers(req);
    }

    @Override
    public void AddUser(User user) {
        userMapper.AddUser(user);
    }

    @Override
    public int checkUser(User user) {
        return userMapper.checkUser(user);
    }
}
