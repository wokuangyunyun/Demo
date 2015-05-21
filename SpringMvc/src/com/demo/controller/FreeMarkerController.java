package com.demo.controller;

import java.util.ArrayList;  
import java.util.List;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
  
  
@Controller  
@RequestMapping("/home")  
public class FreeMarkerController {  
  
    @RequestMapping("/index")  
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {  
  
        User user = new User();  
        user.setUsername("zhangsan");  
        user.setPassword("1234");  
        List<User> users = new ArrayList<User>();  
        users.add(user);  
        return new ModelAndView("ftl.ftl", "users", users);  
    }  
  
}  
