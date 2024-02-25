package com.example.demo.domain.item;

import com.example.demo.domain.exception.NotEnoughException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Getter;

@Entity
@Getter
public class Book extends Item {

    @Override
    @Column(name="Book_id")
    public Long getId() {
        return super.getId(); // 부모 클래스의 getId() 메서드 호출
    }

    private int stockQuantity;

    @JoinColumn(name="book")
    private Lecture lecture;

    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughException("need more stock");
        }
        this.stockQuantity=restStock;
    }
}
