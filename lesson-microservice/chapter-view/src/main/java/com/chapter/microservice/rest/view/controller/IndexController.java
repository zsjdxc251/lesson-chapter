package com.chapter.microservice.rest.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {




    @GetMapping("/home")
    public String home(ModelMap modelMap){


        //modelMap.put("message","message2");


        return "home";
    }


    @ModelAttribute(value = "message")
    public String message(){

        return "xxxlll";
    }


}
