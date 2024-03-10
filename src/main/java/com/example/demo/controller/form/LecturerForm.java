package com.example.demo.controller.form;

import com.example.demo.domain.myClass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerForm extends MemberForm{
    @NotEmpty(message = "과목선택은 필수 입니다")
    private myClass myClass;
}
