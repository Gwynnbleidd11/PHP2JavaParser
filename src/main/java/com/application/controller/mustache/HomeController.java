package com.application.controller.mustache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    @GetMapping("/parser")
    public String home() {
        return "index";
    }
}