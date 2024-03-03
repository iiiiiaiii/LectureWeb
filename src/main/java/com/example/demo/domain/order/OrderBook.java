package com.example.demo.domain.order;

import com.example.demo.domain.item.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orderBook_item")
@NoArgsConstructor
public class OrderBook {
    @Id
    @GeneratedValue
    @Column(name = "orderBook_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private OrderBase orderBase;

    int count;
    int price;

    static OrderBook createOrderBook(Book book, int count, int orderPrice) {
        OrderBook orderBook = new OrderBook();
        orderBook.count=count;
        orderBook.book=book;
        orderBook.price = orderPrice;
        return orderBook;
    }


}