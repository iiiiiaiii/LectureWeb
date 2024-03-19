package com.example.demo.controller;

import org.springframework.ui.Model;
import com.example.demo.annotation.Login;
import com.example.demo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(@Login Member loginMember,Model model){
        log.info("home controller");
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);


        return "loginHome";
    }
}
