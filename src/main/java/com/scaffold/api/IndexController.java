package com.scaffold.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok().build();
    }
}
