package com.lesson.distributed.nginx.controller;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;


/**
 * @author zhengshijun
 * @version created on 2018/10/11.
 */
@Controller
@RequestMapping("/link")
public class HotLinkController {



    @GetMapping("/index")
    public String index(ModelMap modelMap){

        modelMap.addAttribute("message","demo");
        return "home";
    }


//    @ResponseBody
//    @GetMapping("/js/{filename}")
//    public String js(@PathVariable("filename") String filename , HttpServletRequest request){
//
//
//        System.out.println(request.getRequestURI());
//        return filename;
//    }

    @ResponseBody
    @GetMapping("/query.js")
    public ResponseEntity<byte[]> resource(HttpServletRequest request){
        byte[] fullBytes = new byte[1024];
        HttpHeaders headers = new HttpHeaders();
        try {
            URL url = new URL("http://192.168.1.31:8080/adjuncts/e78d6056/org/kohsuke/stapler/jquery/jquery.full.js");
            InputStream inputStream = url.openStream();

            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                arrayOutputStream.write(bytes,0,len);
                arrayOutputStream.flush();
            }
            fullBytes = arrayOutputStream.toByteArray();
            arrayOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<String> enumeration =  request.getHeaderNames();
        while (enumeration.hasMoreElements() ) {
            String name = enumeration.nextElement();
            System.out.println(name+":"+request.getHeader(name));
        }


        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/javascript")).body(fullBytes);

    }



}
