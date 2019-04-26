package com.cry.book.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cry.book.data.LoginRequset;
import com.cry.book.entity.User;

/**
 * 用户信息sql类
 * @author CRY
 *
 */
public interface UserMapper {
    @Select("select * from t_users where user_name=#{req.user.userName}&&password=#{req.user.password}")
    public User getAllUsers(@Param("req") LoginRequset req);

    /**   
     * @Title: 注册用户   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param user      
     * @throws   
     */
    @Insert("insert into t_users(user_name, password) values(#{user.userName}, #{user.password})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    public void AddUser(@Param("user") User user);

    /**   
     * @Title: 检验注册名唯一性   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param user
     * @return      
     * @throws   
     */
    @Select("select count(*) from t_users where user_name=#{user.userName}")
    public int checkUser(@Param("user") User user);
}
