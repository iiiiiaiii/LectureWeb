package com.example.demo.pay;

import com.example.demo.domain.member.Student;

public interface DiscountPolicy {
    public int DiscountPrice(int price, Student student);
}
