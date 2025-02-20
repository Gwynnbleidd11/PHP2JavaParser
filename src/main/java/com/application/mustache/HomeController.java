package com.application.mustache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    @GetMapping("/mustache")
    public String home() {
        return "index";
    }
}
