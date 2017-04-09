package com.qyluo.dao;

import com.qyluo.meta.Content;
import com.qyluo.meta.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Repository
public interface ContentDao {

    //find the content of specific id
    @Select("select * from content where id = #{contentId}")
    @Results({
            @Result(property = "summary", column = "abstract"),
            @Result(property = "image", column = "icon"),
            @Result(property = "detail", column = "text")
    })
    public Product getProduct(@Param("contentId") int contentId);

    //generate the productList in the index page
    @Select("select * from content")
    @Results({
            @Result(property = "summary", column = "abstract"),
            @Result(property = "image", column = "icon"),
            @Result(property = "detail", column = "text")
    })
    public List<Product> getProductList();

}
