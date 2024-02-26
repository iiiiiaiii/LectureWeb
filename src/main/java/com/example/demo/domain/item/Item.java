package com.example.demo.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;


@Getter
public abstract class Item {
    private int price;
    private Long id;
    private String name;


}
