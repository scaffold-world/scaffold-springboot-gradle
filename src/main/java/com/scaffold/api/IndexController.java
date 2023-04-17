package com.scaffold.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("index")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Nice, here is index page!");
    }
}
