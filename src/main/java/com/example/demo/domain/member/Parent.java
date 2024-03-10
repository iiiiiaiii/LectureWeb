package com.example.demo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Parent extends Member{
    @Id
    @GeneratedValue
    @Column(name="parent_id")
    private Long id;


    @OneToOne(fetch = LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    protected Parent() {
    }

    public Parent(int age, String name, String password, String loginId, Student student) {
        super(age, name, password, loginId);
        this.student = student;
    }

    public void addChild(Student student) {

    }
}
