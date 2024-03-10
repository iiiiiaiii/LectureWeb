package com.example.demo.controller;

import com.example.demo.controller.form.BookForm;
import com.example.demo.controller.form.LectureForm;
import com.example.demo.controller.form.LecturerForm;
import com.example.demo.domain.Address;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Grade;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Student;
import com.example.demo.service.ItemService;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/items/new")
    public String FormList() {
        return "items/createItemForm";
    }

    @GetMapping("/items/newBook")
    public String createBookForm(Model model) {
        model.addAttribute("BookForm", new BookForm());
        return "items/createBookForm";
    }

    @PostMapping("/items/newBook")
    public String createItem(@Valid BookForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createStudentForm";
        }
        Book book = new Book(form.getPrice(), itemService.findByName(Lecture.class, form.getLectureName()), form.getName(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());
        itemService.save(book);
        return "redirect:/";
    }

    @GetMapping("/items/newLecture")
    public String createLectureForm(Model model) {
        model.addAttribute("LectureForm", new LectureForm());
        return "items/createLectureForm";
    }

    @PostMapping("/items/newLecture")
    public String createLecture(@Valid LectureForm lectureForm, BindingResult result) {
        if (result.hasErrors()) {
            return "items/createLectureForm";
        }
        Lecture lecture = new Lecture(lectureForm.getPrice(), lectureForm.getName(), memberService.findByNameOne(Lecturer.class, lectureForm.getLecturerName()));
        itemService.save(lecture);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String AllItems(Model model) {
        List<?> allLecture = itemService.findAll(Lecture.class);
        List<?> allBook = itemService.findAll(Book.class);
        model.addAttribute("allBook",allBook);
        model.addAttribute("allLecture",allLecture);
        return "items/itemList";
    }
}