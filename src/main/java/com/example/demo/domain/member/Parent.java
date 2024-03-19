package com.example.demo.domain.member;

import com.example.demo.domain.item.Lecture;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Parent extends Member{
    @Id
    @GeneratedValue
    private Long id;


    private String studentId;
    private List<Lecture> studentLecture = new ArrayList<>();

    protected Parent() {
    }

    public Parent(int age, String name, String password, String loginId,String studentId) {
        super(age, name, password, loginId);
        this.studentId = studentId;
    }

    public void updateLectureList(Lecture lecture) {
        studentLecture.add(lecture);
    }

    public void removeLecture(Lecture lecture) {
        studentLecture.remove(lecture);
    }
}
