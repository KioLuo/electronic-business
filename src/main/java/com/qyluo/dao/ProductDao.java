package com.qyluo.dao;

import com.qyluo.meta.Product;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/8.
 */
@Repository
public interface ProductDao {
    //generate the productList in the index page
    @Select("select a.id, any_value(a.title) as title, any_value(a.price) as price, any_value(a.icon) as icon, any_value(a.abstract) as summary," +
            "any_value(a.text) as detail, any_value(b.time) as buyTime from content a left join trx b on a.id = b.contentId group by a.id")
    public List<Product> getProductList();

}
