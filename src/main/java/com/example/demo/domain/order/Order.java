package com.example.demo.domain.order;

import com.example.demo.domain.member.Student;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Order {
    @Id
    @GeneratedValue
    private Long id;



    @OneToMany(mappedBy = "orderItems")
    private List<OrderItem> orderItems = new ArrayList<>();

    @JoinColumn(name="student")
    private Student student;

    public void setMember(Student student) {
        this.student = student;
        student.getOrders().add(this);
    }
}
