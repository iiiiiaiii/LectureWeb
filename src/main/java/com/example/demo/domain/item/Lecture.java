package com.example.demo.domain.item;

import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Lecture extends Item {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "lecture")
    private List<Book> books = new ArrayList<>();

    public Lecture(int price, String name, Lecturer lecturer, List<Book> books) {
        super(price, name);
        this.lecturer = lecturer;
        this.books = books;
    }
}
