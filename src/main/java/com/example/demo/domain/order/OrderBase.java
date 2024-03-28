package com.example.demo.domain.order;

import com.example.demo.domain.delivery.Delivery;
import com.example.demo.domain.delivery.DeliveryStatus;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.Student;
import com.example.demo.pay.Save;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    @OneToOne(mappedBy = "orderBase",cascade = CascadeType.ALL)
    private OrderLecture orderLecture;

    @OneToMany(mappedBy = "orderBase",cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    private LocalDateTime orderDate; //주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int price;
    public static OrderBase createLecture(Student student, OrderLecture orderLecture) {
        OrderBase orderBase = new OrderBase();
        orderBase.orderDate = LocalDateTime.now();
        orderBase.orderLecture=orderLecture;
        orderBase.student = student;
        orderBase.status = OrderStatus.주문완료;
        orderBase.price= orderLecture.getPrice();
        return orderBase;
    }

    public static OrderBase createBook(Student student,Delivery delivery,OrderBook... orderBooks) {
        OrderBase orderBase = new OrderBase();
        orderBase.price=0;
        orderBase.delivery = delivery;
        orderBase.status = OrderStatus.주문완료;
        orderBase.student = student;
        orderBase.orderBooks.addAll(Arrays.asList(orderBooks));
        orderBase.orderDate = LocalDateTime.now();
        for (OrderBook orderBook : orderBooks) {
            orderBase.price += orderBook.getPrice();
        }
        return orderBase;

    }

    public void cancelLecture() {
        this.setStatus(OrderStatus.주문취소);
    }

    public void cancelBooks() {
        if (delivery.getStatus() == DeliveryStatus.배송중) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.주문취소);
        for (OrderBook orderBook : orderBooks) {
            orderBook.cancel();
        }
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
