package com.example.demo.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public abstract class Member {
    private int age;
    private String name;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }
}
