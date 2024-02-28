package com.example.demo.domain.item;

import com.example.demo.domain.exception.NotEnoughException;
import com.example.demo.domain.member.Student;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Book extends Item {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long id;

    private int price;

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

    protected Book() {
    }

    public Book(int stockQuantity, Lecture lecture) {
        this.stockQuantity = stockQuantity;
        this.lecture = lecture;
    }
}
