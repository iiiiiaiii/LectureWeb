package com.example.demo.domain.delivery;

import jakarta.persistence.*;

@Entity
@Table
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;


    protected Delivery() {
    }
}
