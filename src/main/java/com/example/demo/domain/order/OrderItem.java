package com.example.demo.domain.order;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.Lecture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    private int price;
    private int count;

    public OrderItem() {
    }
    public static OrderItem createOrderLecture(Lecture lecture, int orderPrice) {
        OrderItem orderItem = new OrderItem();
        orderItem.price=orderPrice;
        orderItem.lecture=lecture;
        return orderItem;
    }


    public static OrderItem createOrderBook(Book book, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.price=orderPrice;
        orderItem.count=count;
        orderItem.book=book;
        return orderItem;
    }

}
