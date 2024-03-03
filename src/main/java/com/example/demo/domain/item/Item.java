package com.example.demo.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;
    private int price;
    private String name;

    public Item(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
