package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping
    public String redirectToCourses() {
        return "redirect:/users";
    }
}
