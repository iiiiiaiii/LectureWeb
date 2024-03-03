package com.example.demo.domain.order;

import com.example.demo.domain.item.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orderLecture_item")
@NoArgsConstructor
public class OrderLecture {
    @Id
    @GeneratedValue
    @Column(name = "orderLecture_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = LAZY)
    private OrderBase orderBase;

    private int price;

    public static OrderLecture createOrderLecture(Lecture lecture, int orderPrice) {
        OrderLecture orderLecture = new OrderLecture();
        orderLecture.price=orderPrice;
        orderLecture.lecture = lecture;
        return orderLecture;
    }
}
