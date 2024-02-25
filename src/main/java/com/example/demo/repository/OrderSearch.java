package com.example.demo.repository;

import com.example.demo.domain.order.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
