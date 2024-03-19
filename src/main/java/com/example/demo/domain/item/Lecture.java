package com.example.demo.domain.item;

import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Lecture extends Item {
    @Id
    @GeneratedValue
    @Column(name="lecture_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "lecture")
    private List<Book> books = new ArrayList<>();

    protected Lecture() {
    }

    public Lecture(int price, String name, Lecturer lecturer) {
        super(price,name);
        this.lecturer = lecturer;

    }


}
