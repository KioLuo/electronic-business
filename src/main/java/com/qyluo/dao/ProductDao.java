package com.qyluo.dao;

import com.qyluo.meta.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/8.
 */
@Repository
public interface ProductDao {
    //generate the productList in the index page
    @Select("select a.id as id, any_value(a.title) as title, any_value(a.price) as price, any_value(a.icon) as icon, any_value(a.abstract) as summary," +
            "any_value(a.text) as detail, any_value(b.time) as buyTime from content a left join trx b on a.id = b.contentId group by a.id")
    List<Product> getProductList();

    //delete the product of specific id in table content
    @Update("delete from content where id = #{id}")
    void removeProduct(@Param("id") int id);

    //show the product information of specific id
    @Select("select a.id as id, any_value(a.title) as title, any_value(a.price) as price, any_value(a.icon) as icon, any_value(a.abstract) as summary, " +
            "any_value(a.text) as detail, count(*) as buyNum, any_value(b.time) as buyTime, any_value(b.price) as buyPrice from content a left join " +
            "trx b on a.id = b.contentId where a.id = #{id} group by a.id")
    Product getProduct(@Param("id") int id);

}
