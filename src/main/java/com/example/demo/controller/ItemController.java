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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
            return "/items/newBook";
        }
        Book book = new Book(form.getPrice(), itemService.findByName(Lecture.class, form.getLectureName()), form.getName(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());
        itemService.save(book);
        return "redirect:/";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        List<?> allBook = itemService.findAll(Book.class);
        model.addAttribute("allBook", allBook);
        return "items/bookList";
    }

    @GetMapping("/booklist/{lectureId}")
    public String myBooks(Model model,
                          @PathVariable("lectureId") String Id) {
        Optional<Lecturer> id = memberService.findId(Lecturer.class, Id);
        Lecturer lecturer = id.get();
        List<Book> allBooks = lecturer.getBooks();
        model.addAttribute("allBooks", allBooks);
        return "items/bookList";
    }

    @GetMapping("/{lecturerId}/items/newLecture")
    public String createLectureForm(
            @PathVariable("lecturerId") String id,
            Model model) {
        model.addAttribute("LectureForm", new LectureForm());
        model.addAttribute("lecturerId", id);
        return "items/createLectureForm";
    }

    @PostMapping("/items/newLecture/{lecturerId}")
    public String createLecture(@Valid LectureForm lectureForm, BindingResult result
            , @PathVariable("lecturerId") String id
    ) {
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
        model.addAttribute("allBook", allBook);
        model.addAttribute("allLecture", allLecture);
        return "items/itemList";
    }

    @GetMapping("/lecturelist")
    public String AllLecture(Model model) {
        List<?> allLecture = itemService.findAll(Lecture.class);
        model.addAttribute("allLecture", allLecture);
        return "items/lectureList";
    }

    @GetMapping("/{lectureId}/lecturerHome/lecturelist")
    public String myLectures(Model model,
                             @PathVariable("lectureId") String Id) {
        Optional<Lecturer> id = memberService.findId(Lecturer.class, Id);
        Lecturer lecturer = id.get();
        List<Lecture> allLectures = lecturer.getLectures();
        model.addAttribute("allLectures", allLectures);
        return "items/lectureList";
    }

    @GetMapping("/{studentId}/studentHome/lecturelist")
    public String myLecture(Model model,
                            @PathVariable("studentId") String Id) {
        Optional<Student> id = memberService.findId(Student.class, Id);
        Student student = id.get();
        List<Lecture> allLectures = student.getLectures();
        model.addAttribute("allLectures", allLectures);
        return "items/lectureList";
    }

    @GetMapping("/{studentId}/studentHome/booklist")
    public String myBook(Model model,
                            @PathVariable("studentId") String Id) {
        Optional<Student> id = memberService.findId(Student.class, Id);
        Student student = id.get();
        List<Book> allBook = student.getBooks();
        model.addAttribute("allBook", allBook);
        return "items/bookList";
    }
    @GetMapping("/{studentId}/studentHome/orderlist")
    public String myOrder(Model model,
                         @PathVariable("studentId") String Id) {
        Optional<Student> id = memberService.findId(Student.class, Id);
        Student student = id.get();
        List<Book> allBook = student.getBooks();
        model.addAttribute("allBook", allBook);
        return "items/bookList";
    }
}