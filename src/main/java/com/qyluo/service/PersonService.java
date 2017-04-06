package com.qyluo.service;

import com.qyluo.dao.PersonDao;
import com.qyluo.meta.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ganlee-QY on 2017/4/6.
 */
@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public List<Person> getPersons() {
        return personDao.getPersons();
    }
}
