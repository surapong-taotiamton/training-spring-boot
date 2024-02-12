package com.example.mappingrequest;

import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {


    @GetMapping("hello-world")
    public String helloWorld() {
        return "HELLO WORLD";
    }

    @PutMapping("hello-world-2")
    public String helloWorld2() {
        return "HELLO WORLD 2 PROGRAMMER";
    }

    @RequestMapping(path = "hello-world-3", method = {
            RequestMethod.POST,
            RequestMethod.GET
    })
    public String helloWorld3() {
        return "GET OR POST";
    }


}
