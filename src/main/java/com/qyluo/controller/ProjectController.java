package com.qyluo.controller;

import com.qyluo.meta.Person;
import com.qyluo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Controller
public class ProjectController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value="/index")
    public String showIndex(ModelMap map) {
        Person user = null;
        map.addAttribute("user", user);
        return "index";
    }
}
