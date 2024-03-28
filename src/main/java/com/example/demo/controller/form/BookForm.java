package com.example.demo.controller.form;

import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Lecturer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class BookForm {
    @NotEmpty(message="이름을 입력하세요")
    private String name;
    @Positive(message = "가격을 입력하세요")
    private int price;
    @Positive(message = "재고를 입력하세요")
    private int stockQuantity;
    @NotEmpty(message = "강의는 필수입니다")
    private String lectureName;

    @NotEmpty(message = "저자를 입력하세요")
    private String author;
    @NotEmpty(message = "isbn을 입력하세요")
    private String isbn;
}
