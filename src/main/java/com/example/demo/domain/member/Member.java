package com.example.demo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Member {
    private int age;
    private String name;
    private String password;
    private String LoginId;

    protected Member() {
    }

    public Member(int age, String name, String password, String loginId) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.LoginId = loginId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }
}
