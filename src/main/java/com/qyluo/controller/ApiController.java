package com.qyluo.controller;

import com.qyluo.meta.ApiResult;
import com.qyluo.meta.Person;
import com.qyluo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by qy_lu on 2017/4/9.
 */
@Controller
public class ApiController {
    @Autowired
    PersonService personService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                           HttpServletRequest request, HttpServletResponse response) {
        ApiResult apiResult = null;
        boolean success = false;

        //check the input userName and password if matched
        List<Person> persons = personService.getPersons();
        for (Person p : persons) {
            if (p.getUsername().equals(userName)) {
                if (p.getPassword().equals(password)) {
                    apiResult = new ApiResult(200, "success", true);
                    success = true;

                    //set session for userName and password
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(20 * 60);
                    session.setAttribute("password", password);
                    session.setAttribute("userName", userName);

                    break;
                }
            }
        }
        if (!success) {
            apiResult = new ApiResult(100, "login failed", false);
        }

        return apiResult;
    }
}
