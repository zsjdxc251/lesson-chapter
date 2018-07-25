package com.chapter.microservice.rest.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {




    @GetMapping("/home")
    public String home(ModelMap modelMap){


        //modelMap.put("message","message2");


        return "thymeleaf/home";
    }
    @GetMapping("/list")
    public String list(ModelMap modelMap){


        //modelMap.put("message","message2");


        return "freemarker/home_index";
    }
    @GetMapping("/getSource")
    public String getSource(ModelMap modelMap){


        //modelMap.put("message","message2");


        return "freemarker/source";
    }

    @GetMapping("/jsonString")
    @ResponseBody
    public String jsonString(){
        return  "jsonString222";

    }



    @ModelAttribute(value = "message")
    public String message(){

        return "大佬三连鄙视";
    }


}
