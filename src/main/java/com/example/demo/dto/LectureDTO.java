package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LectureDTO {
    private String name;
    private int price;
    private String lecturerName;
    private List<String> bookList = new ArrayList<>();

    public LectureDTO(String name, int price, String lecturerName) {
        this.name = name;
        this.price = price;
        this.lecturerName = lecturerName;
    }
}
