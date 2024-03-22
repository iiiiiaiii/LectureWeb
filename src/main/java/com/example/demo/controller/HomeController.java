package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }

    @RequestMapping("/studentHome")
    public String SHome() {
        return "studentHome";
    }

    @RequestMapping("/parentHome")
    public String PHome() {
        return "parentHome";
    }

    @RequestMapping("/lecturerHome")
    public String LHome() {
        return "lecturerHome";
    }

}
