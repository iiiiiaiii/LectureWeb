package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트스캔대상, 자동으로 빈등록
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public <T> T findOne(Class<T> entityClass, Long id) {
        return memberRepository.findOne(entityClass, id);
    }

    public List<?> findAll(Class<?> entityClass) {
        return memberRepository.findAll(entityClass);
    }

    public void deleteMember(Class<?> entityClass,Long id) {
        memberRepository.delete(entityClass,id);
    }

    public void updateMember(Class<?> entityClass,Long id, String loginId, String name, int age, int password) {
        memberRepository.findOne(entityClass, id);
    }


}
