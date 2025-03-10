package com.example.RestCrudWithJpa;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {



    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }


}
