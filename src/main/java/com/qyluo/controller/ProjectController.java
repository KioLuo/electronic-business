package com.qyluo.controller;

import com.qyluo.meta.Person;
import com.qyluo.meta.Product;
import com.qyluo.service.PersonService;
import com.qyluo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qy_lu on 2017/4/4.
 */
@Controller
public class ProjectController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ProductService productService;

    /**
     *mapping index page
     */
    @RequestMapping(value="/")
    public String showIndex(ModelMap map, HttpSession session, HttpServletRequest request) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        //determine the listType
        int type = 0;
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }
        Map<String, Integer> RequestParameters = new HashMap<String, Integer>();
        RequestParameters.put("type", type);
        map.addAttribute("RequestParameters", RequestParameters);

        //add the productList
        List<Product> productList = productService.getProductList();
        map.addAttribute("productList", productList);

        return "index";
    }

    /**
     * mapping login page
     */
    @RequestMapping("/login")
    public String showLogin(HttpServletRequest request, ModelMap map) {
        Person user = null;
        map.addAttribute("user", user);

        return "login";
    }

    /**
     * logout and return login page
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, ModelMap map) {
        session.invalidate();
        Person user = null;
        map.addAttribute("user", user);
        return "login";
    }

    /**
     * get and check the userName and password in session
     */
    public Person checkUser(HttpSession session) {
        Person user = null;
        String userName;
        String password;

        password = (String) session.getAttribute("password");
        userName = (String) session.getAttribute(("userName"));

        List<Person> persons = personService.getPersons();
        if (userName != null && password != null) {
            for (Person person : persons) {
                if (person.getUsername().equals(userName)) {
                    if (person.getPassword().equals(password)) {
                        user = person;
                        break;
                    }
                }
            }
        }
        return user;
    }
}
