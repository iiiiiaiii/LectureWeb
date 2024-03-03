package com.example.demo.domain.member;

import com.example.demo.domain.Address;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.order.OrderBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class Student extends Member {
    @Embedded
    private Address address;
    private int pay;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToOne(mappedBy = "student")
    private Parent parent;

    @OneToMany
    private List<Lecture> lectures = new ArrayList<>();

    @OneToMany
    private List<Book> books = new ArrayList<>();


    @OneToMany(mappedBy = "student")
    private List<OrderBase> orderBases = new ArrayList<>();

    public Student(int age, String name, int password, String loginId, Address address, Grade grade,Parent parent) {
        super(age, name, password, loginId);
        this.address = address;
        this.grade = grade;
        this.parent=parent;
        this.pay=0;
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
