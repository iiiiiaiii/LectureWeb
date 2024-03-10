package com.example.demo.controller.form;

import com.example.demo.domain.member.Lecturer;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

public class LectureForm {
    @NotEmpty(message = "책 이름은 필수입니다")
    private String name;
    private String lecturerName;
    private int price;


}
