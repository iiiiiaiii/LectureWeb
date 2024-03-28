package com.example.demo.controller.form;

import com.example.demo.domain.member.Lecturer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class LectureForm {
    @NotEmpty(message = "강의 이름을 입력하세요")
    private String lectureName;
    @Positive(message = "가격을 입력하세요")
    private int price;


}
