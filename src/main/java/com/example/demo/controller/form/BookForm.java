package com.example.demo.controller.form;

import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Lecturer;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    private String name;
    private int price;
    private int stockQuantity;
    @NotEmpty(message = "강의는 필수입니다")
    private String lectureName;

    private String author;
    private String isbn;
}
