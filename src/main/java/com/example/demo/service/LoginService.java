package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findById(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
