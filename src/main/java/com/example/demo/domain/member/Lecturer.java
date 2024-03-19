package com.example.demo.domain.member;

import com.example.demo.domain.myClass;
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
    private Long lecturerId;

    @OneToMany(mappedBy = "lecturer")
    private List<Lecture> lectures = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private myClass myClass;

    protected Lecturer() {
    }

    public Lecturer(int age, String name, String password, String loginId, myClass myClass) {
        super(age, name, password, loginId);
        this.myClass = myClass;
    }

}
