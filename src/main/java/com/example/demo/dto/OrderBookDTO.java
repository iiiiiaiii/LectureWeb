package com.example.demo.dto;

import com.example.demo.domain.delivery.DeliveryStatus;
import com.example.demo.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderBookDTO {
    private String bookName;
    private String lectureName;
    private String lecturerName;
    private String ISBN;
    private LocalDateTime orderDate;
    private int count;
    private String author;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;

    public OrderBookDTO(String bookName, String lectureName, String lecturerName, String ISBN, LocalDateTime orderDate, int count, String author,OrderStatus orderStatus,DeliveryStatus deliveryStatus) {
        this.bookName = bookName;
        this.lectureName = lectureName;
        this.lecturerName = lecturerName;
        this.ISBN = ISBN;
        this.orderDate = orderDate;
        this.count = count;
        this.author = author;
        this.orderStatus = orderStatus;
        this.deliveryStatus = deliveryStatus;
    }
}
