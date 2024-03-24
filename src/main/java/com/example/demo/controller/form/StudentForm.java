package com.example.demo.controller.form;

import com.example.demo.domain.member.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class StudentForm extends MemberForm{
    @NotEmpty(message = "주소 입력은 필수 입니다")
    private String city;
    private String street;
    private String zipcode;
}