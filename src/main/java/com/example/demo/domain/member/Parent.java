package com.example.demo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor

public class Parent extends Member{
    @OneToOne(fetch = LAZY)
    @JoinColumn(name="student_id")
    private Student student;
    public Parent(int age, String name, int password, String loginId, Student student) {
        super(age, name, password, loginId);
        this.student = student;
    }
}
