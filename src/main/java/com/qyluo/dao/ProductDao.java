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
    @Select("select a.id as id, a.title as title, a.price as price, a.icon as icon, a.abstract as summary," +
            "a.text as detail, b.time as buyTime from content a left join trx b on a.id = b.contentId group by a.id")
    List<Product> getProductList();

    //delete the product of specific id in table content
    @Update("delete from content where id = #{id}")
    void removeProduct(@Param("id") int id);

    //show the product information of specific id
    @Select("select a.id as id, a.title as title, a.price as price, a.icon as icon, a.abstract as summary, " +
            "a.text as detail, count(*) as buyNum, b.time as buyTime, b.price as buyPrice from content a left join " +
            "trx b on a.id = b.contentId where a.id = #{id} group by a.id")
    Product getProduct(@Param("id") int id);

    //add product information in table content
    @Insert("insert into content (price, title, icon, abstract, text) values (#{product.price}, #{product.title}, #{product.icon}, #{product.summary}, #{product.detail})")
    @Options(useGeneratedKeys = true, keyProperty = "product.id")
    void addProduct(@Param("product") Product product);

    //update product information in table content
    @Update("update content set price = #{product.price}, title = #{product.title}, icon = #{product.icon}, abstract = #{product.summary}, text = #{product.detail} where id = #{product.id}")
    void updateProduct(@Param("product") Product product);

    //add transaction in table trx
    @Insert("insert into trx (contentId, personId, price, time) values (#{contentId}, 0, #{price}, #{time})")
    void addTransaction(@Param("contentId") int contentId, @Param("price") int price, @Param("time") long time);

    //get the transaction list in table trx
    @Select("select b.contentId as id, a.title as title, a.price as price, a.icon as icon, a.abstract as summary, " +
            "a.text as detail, count(*) as buyNum, b.time as buyTime, b.price as buyPrice from content a right join " +
            "trx b on a.id = b.contentId group by b.contentId")
    List<Product> getBuyList();


}
