package com.lesson.microservice.boot.version.controller;


import com.lesson.microservice.boot.version.annotation.ApiVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *   版本控制
 * @author zhengshijun
 */
@RestController
@RequestMapping("/{version}/version")
public class VersionController {

    @ApiVersion(1)
    @GetMapping("/load")
    public ResponseEntity<String> loadMessageV1(){

        return ResponseEntity.ok("loadMessageV1");
    }

    @ApiVersion(2)
    @GetMapping("/load")
    public ResponseEntity<String> loadMessageV2(){

        return ResponseEntity.ok("loadMessageV2");
    }

    @ApiVersion(2)
    @GetMapping("/loadName")
    public ResponseEntity<String> loadNameV2(){

        return ResponseEntity.ok("loadNameV2");
    }

    @ApiVersion(1)
    @GetMapping("/loadName")
    public ResponseEntity<String> loadNameV1(){

        return ResponseEntity.ok("loadNameV2");
    }


    @GetMapping("/loadType")
    public ResponseEntity<String> loadType(@PathVariable("version") int version){

        return ResponseEntity.ok("loadType"+version);
    }




}
