package com.example.httpresponse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateEngineController {

    @GetMapping("test-template")
    public String renderMyTemplate(
            @RequestParam("name") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "mytemplate.html";
    }

}
