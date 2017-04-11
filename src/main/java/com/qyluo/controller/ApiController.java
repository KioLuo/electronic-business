package com.qyluo.controller;

import com.qyluo.meta.ApiResult;
import com.qyluo.meta.ApiStringResult;
import com.qyluo.meta.Person;
import com.qyluo.meta.Product;
import com.qyluo.service.PersonService;
import com.qyluo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by qy_lu on 2017/4/9.
 */
@Controller
public class ApiController {
    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

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

    @RequestMapping(value = "/api/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult deleteProduct(@RequestParam("id") int id, HttpServletRequest request) {
        productService.removeProduct(id);
        ApiResult apiResult = new ApiResult(200, "success", true);
        return apiResult;
    }

    @RequestMapping(value = "/api/buy", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult buyProducts(@RequestParam("data") Object id, @RequestParam("number") Object number) {
//        for (int i = 0; i < id.size(); i++) {
//            int price = productService.showProduct(id.get(i)).getPrice();
//            long time = new Date().getTime();
//            for (int j = 0; j < number.get(i); j++) {
//                productService.addTransaction(id.get(i), price, time);
//            }
//        }

        return new ApiResult(200, "success", true);
    }

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    @ResponseBody
    public ApiStringResult uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String today = sdf.format(date);
        String url = "/image/" + today + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String realPath = request.getServletContext().getRealPath("/") + url;
        try {
            OutputStream out = new FileOutputStream(new File(realPath));
            out.write(file.getBytes());
            out.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return new ApiStringResult(200, "success", url);
    }
}
