package com.example.demo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Parent extends Member{
    @Id
    @GeneratedValue
    @Column(name="parent_id")
    private Long id;


    @OneToOne
    private Student student;

    protected Parent() {
    }

    public Parent(int age, String name, int password, String loginId, Student student) {
        super(age, name, password, loginId);
        this.student = student;
    }
}
