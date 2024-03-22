package com.example.demo.controller;

import com.example.demo.controller.form.*;
import com.example.demo.domain.Address;
import com.example.demo.domain.member.Grade;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Parent;
import com.example.demo.domain.member.Student;
import com.example.demo.domain.myClass;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.myClass.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/members/new")
    public String FormList() {
        return "members/createMemberForm";
    }

    @GetMapping("/members/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("studentForm",new StudentForm());
        return "members/createStudentForm";
    }

    @PostMapping("/members/newStudent")
    public String createStudent(@Valid StudentForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createStudentForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Student student = new Student(form.getAge(), form.getName(), form.getPassword(), form.getLoginId(), address, Grade.일반);

        Optional<Student> findId = memberService.findId(Student.class, form.getLoginId());
        if (findId.isPresent()) {
            result.reject("IdError", "이미 존재하는 Id입니다");
            return "members/createStudentForm";
        }
        memberService.join(student);
        return "redirect:/";
    }

    @GetMapping("/members/newParent")
    public String newParent(Model model) {
        model.addAttribute("parentForm",new ParentForm());
        return "members/createParentForm";
    }
    @PostMapping("/members/newParent")
    public String createParent(@Valid ParentForm form, BindingResult result
    ) {
        if (result.hasErrors()) {
            return "members/createParentForm";

        }
        Optional<Student> findStudent = memberService.findId(Student.class, form.getChildId());
        Student student = findStudent.get();

        Parent parent = new Parent(form.getAge(), form.getName(), form.getPassword(), form.getLoginId(), student);

        Optional<Parent> findId = memberService.findId(Parent.class, form.getLoginId());
        if (findId.isPresent()) {
            result.reject("IdError", "이미 존재하는 Id입니다");
            return "members/createParentForm";
        }
        if(findStudent.isEmpty()){
            result.reject("childIdError", "존재하지 않는 자녀 ID입니다.");
            return "members/createParentForm";
        }

        memberService.join(parent);
        return "redirect:/";
    }

    @GetMapping("/members/newLecturer")
    public String newLecturer(Model model) {
        model.addAttribute("lecturerForm",new LecturerForm());
        return "members/createLecturerForm";
    }

    @PostMapping("/members/newLecturer")
    public String createLecturer(@Valid Lecturer form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createLecturerForm";
        }
        myClass subject = myClass.valueOf(String.valueOf(form.getMyClass()));
        Lecturer lecturer = new Lecturer(form.getAge(), form.getName(), form.getPassword(), form.getLoginId(), subject);

        Optional<Lecturer> findId = memberService.findId(Lecturer.class, form.getLoginId());
        if (findId.isPresent()) {
            result.reject("IdError", "이미 존재하는 Id입니다");
            return "members/createLecturerForm";
        }
        memberService.join(lecturer);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<?> allStudent = memberService.findAll(Student.class);
        List<?> allParent = memberService.findAll(Parent.class);
        List<?> allLecturer = memberService.findAll(Lecturer.class);


        model.addAttribute("allStudent", allStudent);
        model.addAttribute("allParent", allParent);
        model.addAttribute("allLecturer", allLecturer);
        return "members/memberList";
    }

}
