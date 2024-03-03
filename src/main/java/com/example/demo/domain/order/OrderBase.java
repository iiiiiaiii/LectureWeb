package com.example.demo.domain.order;

import com.example.demo.domain.delivery.Delivery;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class OrderBase {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @OneToMany(mappedBy = "orderBase")
    private List<OrderLecture> orderLectures = new ArrayList<>();

    @OneToOne(mappedBy = "orderBase")
    private OrderBook orderBook;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Student student;


//    public static OrderBase createOrder(Student student, Delivery delivery) {
//        OrderBase orderBase = new OrderBase();
//        orderBase.
//    }
}
