package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트스캔대상, 자동으로 빈등록
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }


    private void validateDuplicateMember(Class<?> entityClass,Member member) {
        List<?> findMember = memberRepository.findByName(entityClass, member.getName());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    public <T> T findByNameOne(Class<T> entityClass, String name) {
        return memberRepository.findByNameOne(entityClass, name);
    }


    public <T> T findOne(Class<T> entityClass, Long id) {
        return memberRepository.findOne(entityClass, id);
    }

    public List<?> findAll(Class<?> entityClass) {
        return memberRepository.findAll(entityClass);
    }

    @Transactional
    public void deleteMember(Class<?> entityClass,Long id) {
        memberRepository.delete(entityClass,id);
    }

   @Transactional
    public void updateMember(Class<?> entityClass,Long id, String loginId, String name, int age, int password) {
        memberRepository.findOne(entityClass, id);
    }


}
