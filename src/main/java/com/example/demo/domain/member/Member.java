package com.example.demo.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public abstract class Member {
    private int age;
    private String name;

    @Id
    @GeneratedValue
    @Column(name="회원번호")
    private Long id;

    private int password;
    private String LoginId;

    protected Member() {
    }

    public Member(int age, String name, int password, String loginId) {
        this.age = age;
        this.name = name;
        this.password = password;
        LoginId = loginId;
    }
}
