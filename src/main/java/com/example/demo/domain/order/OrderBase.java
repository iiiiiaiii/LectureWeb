package com.example.demo.domain.order;

import com.example.demo.domain.member.Student;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderBase {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @OneToMany(mappedBy = "orderBase")
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="student_id")
    private Student student;

    protected OrderBase() {
    }
}
