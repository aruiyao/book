/**
 * 
 */
package com.cry.book.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cry.book.data.LoginRequset;
import com.cry.book.entity.User;
import com.cry.book.service.UserService;

/**
 * @author CRY
 *
 */
@Controller
public class UserControlle {
    @Autowired
    private UserService userService;

    /**   
     * @Title: 用户登录   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param req
     * @param request
     * @return      
     * @throws   
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public User login(@RequestBody LoginRequset req, HttpServletRequest request) {

        User user = userService.getAllUsers(req);
        if (user == null) {
            user = new User();
        }
        request.getSession().setMaxInactiveInterval(3600);
        HttpSession session = request.getSession();// 这就是session的创建
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("id", user.getId());

        return user;
    }

    /**   
     * @Title: 退出登录   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param request      
     * @throws   
     */
    @GetMapping(value = "/logout")
    @ResponseBody
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();// 这就是session的创建
        session.invalidate();
    }

    /**   
     * @Title: 获取登录的用户信息   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param request
     * @return      
     * @throws   
     */
    @GetMapping(value = "/getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpServletRequest request) {
        User user = new User();
        HttpSession session = request.getSession();// 这就是session的创建
        String userName = (String) session.getAttribute("userName");
        Integer id = (Integer) session.getAttribute("id");
        user.setUserName(userName);
        user.setId(id);
        return user;
    }

    /**   
     * @Title: 注册用户   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param user
     * @param request      
     * @throws   
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public void register(@RequestBody User user, HttpServletRequest request) {
        userService.AddUser(user);
        HttpSession session = request.getSession();// 这就是session的创建
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("id", user.getId());
    }

    /**   
     * @Title: 注册学号唯一性校验   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param user
     * @return      
     * @throws   
     */
    @GetMapping(value = "/checkUser")
    @ResponseBody
    public Map < String, Boolean > checkUser(User user) {
        System.out.println(user);
        Map < String, Boolean > map = new HashMap < String, Boolean >();
        int isExist = userService.checkUser(user);
        System.out.println("isExist=" + isExist);
        if (isExist == 0) {
            // 注册学号不存在
            map.put("valid", true);
        }
        else {
            // 注册学号已存在
            map.put("valid", false);
        }
        return map;
    }

}
