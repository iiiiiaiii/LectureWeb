package com.example.demo.domain.member;

import com.example.demo.domain.aClass;
import com.example.demo.domain.item.Lecture;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Lecturer extends Member {
    @Id
    @GeneratedValue
    @Column(name="lecturer_id")
    private Long id;

    @OneToMany(mappedBy = "lecturer")
    private final List<Lecture> lectures = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private aClass aClass;

    protected Lecturer() {
    }

    public Lecturer(int age, String name, int password, String loginId,aClass aClass) {
        super(age, name, password, loginId);
        this.aClass = aClass;

    }
}
