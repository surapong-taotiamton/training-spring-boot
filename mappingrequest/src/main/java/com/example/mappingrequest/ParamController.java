package com.example.mappingrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamController {


    @GetMapping("test-param")
    public String mappingTest(
            @RequestParam(name = "name", required = false) String inputParameter,
            @RequestParam(name = "age", required = false) Integer inputAge
    ) {
        return String.format("Name = %s  Age = %d", inputParameter, inputAge);
    }

}
