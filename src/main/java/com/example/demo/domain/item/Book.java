package com.example.demo.domain.item;

import com.example.demo.domain.exception.NotEnoughException;
import com.example.demo.domain.member.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Book extends Item {
    private int stockQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="lecture_id")
    private Lecture lecture;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughException("need more stock");
        }
        this.stockQuantity=restStock;
    }

    public Book( int price, String name, int stockQuantity, Lecture lecture) {
        super(price, name);
        this.stockQuantity = stockQuantity;
        this.lecture = lecture;
    }
}
