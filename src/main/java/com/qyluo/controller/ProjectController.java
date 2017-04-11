package com.qyluo.controller;

import com.qyluo.meta.Person;
import com.qyluo.meta.Product;
import com.qyluo.service.PersonService;
import com.qyluo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * show index page
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
     * show login page
     */
    @RequestMapping("/login")
    public String showLogin(HttpServletRequest request, ModelMap map) {
        Person user = null;
        map.addAttribute("user", user);

        return "login";
    }

    /**
     * logout and jump to login page
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, ModelMap map) {
        session.invalidate();
        Person user = null;
        map.addAttribute("user", user);
        return "login";
    }

    /**
     * show the show page
     */
    @RequestMapping("/show")
    public String showProduct(@RequestParam("id") int id, HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        Product product = productService.showProduct(id);
        map.addAttribute("product", product);

        return "show";
    }

    /**
     * show the settle account page
     */
    @RequestMapping("/settleAccount")
    public String settleAccount(HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);
        return "settleAccount";
    }

    /**
     * show the account page
     */
    @RequestMapping("/account")
    public String showAccount(HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        List<Product> buyList = productService.getBuyList();
        map.addAttribute("buyList", buyList);

        return "account";
    }

    /**
     * show the public page
     */
    @RequestMapping("/public")
    public String showPublic(HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        return "public";
    }

    /**
     * show the public submit page
     */
    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String showPublicSubmit(@RequestParam("title") String title, @RequestParam("image") String image,
                                   @RequestParam("detail") String detail, @RequestParam("summary") String summary,
                                   @RequestParam("price") int price, HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        Product product = productService.addProduct(price, title, image, summary, detail);
        map.addAttribute("product", product);

        return "publicSubmit";
    }

    /**
     * show the edit page
     */
    @RequestMapping("/edit")
    public String showEdit(@RequestParam("id") int id, HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        Product product = productService.showProduct(id);
        map.addAttribute("product", product);

        return "edit";
    }

    /**
     * show the public submit page
     */
    @RequestMapping(value = "/editSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public String showEditSubmit(@RequestParam("title") String title, @RequestParam("image") String image,
                                   @RequestParam("detail") String detail, @RequestParam("summary") String summary,
                                   @RequestParam("price") int price, @RequestParam("id") int id, HttpSession session, ModelMap map) {
        Person user = checkUser(session);
        map.addAttribute("user", user);

        Product product = productService.updateProduct(price, title, image, summary, detail, id);
        map.addAttribute("product", product);

        return "editSubmit";
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
