package com.example.demo.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public abstract class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private int age;
    private String name;
    private int password;
    private String LoginId;

    public Member(int age, String name, int password, String loginId) {
        this.age = age;
        this.name = name;
        this.password = password;
        LoginId = loginId;
    }
}
