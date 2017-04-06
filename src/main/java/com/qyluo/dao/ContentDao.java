package com.qyluo.dao;

import com.qyluo.meta.Content;
import org.apache.ibatis.annotations.*;

/**
 * Created by qy_lu on 2017/4/4.
 */
public interface ContentDao {
    @Select("select * from content where id = #{contentId}")
    @Results({
            @Result(property = "abstra", column = "abstract")
    })
    public Content getContent(@Param("contentId") int contentId);

    @Update("insert into content ('price', 'title', 'icon', 'abstract', 'text') values (#{price}, #{title}, #{icon}, #{abstra}, #{text})")
    public void addContent(@Param("price") int price, @Param("title") String title, @Param("icon") byte[] icon, @Param("abstra") String abstra, @Param("text") String text);
}
