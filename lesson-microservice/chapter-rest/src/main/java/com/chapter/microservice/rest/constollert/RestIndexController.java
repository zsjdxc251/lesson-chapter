package com.chapter.microservice.rest.constollert;

import com.chapter.microservice.rest.annotation.CustomAnnotation;
import com.chapter.microservice.rest.entitry.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CustomAnnotation(paths = "/index")
public class RestIndexController {

    private static final Logger log = LoggerFactory.getLogger(RestIndexController.class);


    @ResponseBody
    @PostMapping("/home")
    public ResponseEntity<Person> home(@RequestParam Person person){


        log.info("person :{}",person);
        if (person == null){

        }

        return ResponseEntity.ok(new Person("zsj","深圳",22));
    }


    @ResponseBody
    @GetMapping("/source/get")
    public ResponseEntity<Person> getSource(boolean cache){
 
        if (cache) {

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new Person("zsj","深圳",20));

        }

        return ResponseEntity.ok(new Person("zsj","深圳",20));
    }
}
