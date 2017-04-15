package com.qyluo.dao;

import com.qyluo.meta.Person;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Repository
public interface PersonDao {
    @Select("select * from person")
    @Results({
            @Result(property = "username", column = "userName"),
            @Result(property = "nickname", column = "nickName"),
            @Result(property = "usertype", column = "userType")
    })
    List<Person> getPersons();
}
