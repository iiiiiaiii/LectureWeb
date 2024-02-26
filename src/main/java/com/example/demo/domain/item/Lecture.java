package com.example.demo.domain.item;

import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Student;
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


    private int price;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "lectures")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "lecture")
    private List<Book> books = new ArrayList<>();

    protected Lecture() {
    }

    public Lecture(int price, String name, Student student, Lecturer lecturer, List<Book> books) {
        this.price = price;
        this.name = name;
        this.student = student;
        this.lecturer = lecturer;
        this.books = books;
    }
}
