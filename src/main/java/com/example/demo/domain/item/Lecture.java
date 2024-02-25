package com.example.demo.domain.item;

import com.example.demo.domain.member.Lecturer;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Lecture extends Item {

    @Override
    @Column(name="Lecture_id")
    public Long getId() {
        return super.getId(); // 부모 클래스의 getId() 메서드 호출
    }

    private int price;

    private String name;

    @JoinColumn (name="lectures")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "lecture")
    private Book book;

}
