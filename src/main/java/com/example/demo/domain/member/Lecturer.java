package com.example.demo.domain.member;

import com.example.demo.domain.aClass;
import com.example.demo.domain.item.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class Lecturer extends Member {
    @OneToMany(mappedBy = "lecturer")
    private List<Lecture> lectures = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private aClass aClass;

    public Lecturer(int age, String name, int password, String loginId,aClass aClass) {
        super(age, name, password, loginId);
        this.aClass = aClass;
    }

}
