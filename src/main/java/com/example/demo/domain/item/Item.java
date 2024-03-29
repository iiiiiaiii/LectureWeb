package com.example.demo.domain.item;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@MappedSuperclass
public abstract class Item {
    private int price;
    private String name;

    public Item(int price, String name) {
        this.price=price;
        this.name=name;
    }

    protected Item() {
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name=name;
    }
}
