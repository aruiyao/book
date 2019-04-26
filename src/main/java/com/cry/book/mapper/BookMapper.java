package com.cry.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cry.book.entity.Book;

public interface BookMapper {
    @Insert("insert into t_book"
            + "(name,picture,author,press,release_time,page,type,price,book_brife,auth_brife,create_time) values "
            + "(#{name},#{picture},#{author},#{press},#{releaseTime},#{page},#{type},#{price},#{bookBrife},#{authBrife},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void addBook(Book book);
    
    @Select({
        "<script>",
        "select * from t_book where 1=1",
        "<if test='id!=null'>",
        "and id=#{id}",
        "</if>",
        "<if test='name!=null and name!=\"\"'>",
        "and name like CONCAT(CONCAT('%', #{name}), '%')",
        "</if>",
        "<if test='type!=null and type!=\"\"'>",
        "and type=#{type}",
        "</if>",
        "order by create_time desc",
        "</script>"
    })
    public List<Book> queryBookByCond(Book book);
    
    @Update({"<script>",
        "update t_book",
        "<set>",
            "name=#{name},picture=#{picture},author=#{author},press=#{press},release_time=#{releaseTime},page=#{page},type=#{type},price=#{price},book_brife=#{bookBrife},auth_brife=#{authBrife}",        
        "</set>",
        "where id=#{id}",
        "</script>" 
    })
    public void updateBook(Book book);
    
    @Delete("delete from t_book where id=#{0}")
    public void deleteBook(Integer id);
}
