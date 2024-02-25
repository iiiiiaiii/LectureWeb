package com.example.demo.domain.member;

import com.example.demo.domain.Address;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Student extends Member {

    @Embedded
    private Address address;

    private int pay;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "student")
    private final List<Lecture> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private final List<Book> books = new ArrayList<>();


    @OneToMany(mappedBy = "orders")
    private List<Order> orders = new ArrayList<>();

    protected Student() {
    }

    public Student(int age, String name, int password, String loginId, Address address, int pay, Grade grade, List<Order> orders) {
        super(age, name, password, loginId);
        this.address = address;
        this.pay = pay;
        this.grade = grade;
        this.orders = orders;
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
