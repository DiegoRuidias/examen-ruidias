package com.ruidias.Examen01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/infracciones")
    public String infracciones() {
        return "infracciones";
    }

}
