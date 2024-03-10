package com.example.demo.controller.form;

import com.example.demo.domain.member.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor

public class ParentForm extends MemberForm{
    @NotEmpty(message = "자녀 입력은 필수 입니다")
    private Long childId;

}
