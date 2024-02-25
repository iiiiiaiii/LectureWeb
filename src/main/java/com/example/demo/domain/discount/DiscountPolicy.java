package com.example.demo.domain.discount;

import com.example.demo.domain.member.Student;

public interface DiscountPolicy {
    public int DiscountPrice(int price, Student student);
}
