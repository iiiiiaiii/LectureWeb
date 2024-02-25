package com.example.demo.domain.discount;

import com.example.demo.domain.member.Grade;
import com.example.demo.domain.member.Student;

public abstract class Save {
    protected void save(int price, Student student) {
        int pay = student.getPay();
        int plus = (int) (pay * 0.02);
        student.setPay(pay + price);
        int mileage = student.getMileage();
        student.setMileage(mileage + plus);
        gradeSet(student);
    }

    private void gradeSet(Student student) {
        if(student.getGrade()== Grade.골드) return;
        if(student.getPay()<100000) student.setGrade(Grade.일반);
        else if(student.getPay()<500000) student.setGrade(Grade.실버);
        else student.setGrade(Grade.골드);
    }
}
