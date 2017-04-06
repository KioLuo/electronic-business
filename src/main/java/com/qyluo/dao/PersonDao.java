package com.qyluo.dao;

import com.qyluo.meta.Person;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by qy_lu on 2017/4/4.
 */
public interface PersonDao {
    @Select("select * from person")
    public List<Person> getPersons();
}
