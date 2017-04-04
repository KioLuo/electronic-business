package com.qyluo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Controller
public class ProjectController {
    @RequestMapping(value="/index")
    public String showIndex() {
        return "index";
    }
}
