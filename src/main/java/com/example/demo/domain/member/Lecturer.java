package com.example.demo.domain.member;

import com.example.demo.domain.item.Lecture;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Lecturer extends Member {
    @OneToMany(mappedBy = "lecturer")
    private final List<Lecture> lectures = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Class aClass;

    protected Lecturer() {
    }

    public Lecturer(int age, String name, int password, String loginId,Class aClass) {
        super(age, name, password, loginId);
        this.aClass = aClass;

    }
}
