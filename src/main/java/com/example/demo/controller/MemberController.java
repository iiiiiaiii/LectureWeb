package com.example.demo.controller;

import com.example.demo.controller.form.*;
import com.example.demo.domain.Address;
import com.example.demo.domain.member.Grade;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Parent;
import com.example.demo.domain.member.Student;
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

        memberService.join(student);
        return "redirect:/";
    }

    @GetMapping("/members/newParent")
    public String newParent(Model model) {
        model.addAttribute("parentForm",new ParentForm());
        return "members/createParentForm";
    }
    @PostMapping("/members/newParent")
    public String createParent(@Valid ParentForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createPrentForm";
        }
        Parent parent = new Parent(form.getAge(), form.getName(), form.getPassword(), form.getLoginId(),form.getChildId());
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
            return "members/createStudentForm";
        }
        Lecturer lecturer = new Lecturer(form.getAge(), form.getName(), form.getPassword(), form.getLoginId(), form.getMyClass());
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
