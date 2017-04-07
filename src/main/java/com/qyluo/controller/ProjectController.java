package com.qyluo.controller;

import com.qyluo.meta.Person;
import com.qyluo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Controller
public class ProjectController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value="/index.do")
    public String showIndex(ModelMap map, HttpServletRequest request) {
        Person user = personService.getPersons().get(0);
        map.addAttribute("user", user);
        request.setAttribute("type", user.getUsertype());

        return "index";
    }
}
