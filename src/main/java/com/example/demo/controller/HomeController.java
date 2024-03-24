package com.example.demo.controller;

import com.example.demo.domain.member.Student;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }

    @RequestMapping("/{studentId}/studentHome")
    public String SHome(@PathVariable("studentId") String studentId) {
        return "studentHome";
    }

    @RequestMapping("/{parentId}/parentHome")
    public String PHome(@PathVariable("parentId") String parentId) {
        return "parentHome";
    }

    @RequestMapping("/{lecturerId}/lecturerHome")
    public String LHome(@PathVariable("lecturerId")String lecturerId) {
        return "lecturerHome";
    }

}
