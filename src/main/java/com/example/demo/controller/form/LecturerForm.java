package com.example.demo.controller.form;

import com.example.demo.domain.myClass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LecturerForm extends MemberForm{
    @NotNull(message = "과목선택은 필수 입니다")
    private myClass myClass;
}
