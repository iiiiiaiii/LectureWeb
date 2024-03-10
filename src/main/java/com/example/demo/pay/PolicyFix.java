package com.example.demo.pay;

import com.example.demo.domain.member.Student;

public class PolicyFix extends Save implements DiscountPolicy{

    @Override
    public int DiscountPrice(int price, Student student) {
        save(price, student);
        return price - 1000;
    }
}
