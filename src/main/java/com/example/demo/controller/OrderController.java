package com.example.demo.controller;

import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final MemberRepository memberRepository;


}
