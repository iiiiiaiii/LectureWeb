package com.example.demo.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Parent extends Member{

    @OneToOne
    private Student student;

    protected Parent() {
    }

    public Parent(int age, String name, int password, String loginId, Student student) {
        super(age, name, password, loginId);
    }
}
