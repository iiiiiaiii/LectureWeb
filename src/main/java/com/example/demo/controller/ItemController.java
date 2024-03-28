package com.example.demo.controller;

import com.example.demo.controller.form.BookForm;
import com.example.demo.controller.form.LectureForm;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Parent;
import com.example.demo.domain.member.Student;
import com.example.demo.service.ItemService;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/{lecturerId}/lecturerHome/booklist")
    public String myBooks(Model model,
                          @PathVariable("lecturerId") String lecturerId) {
        Optional<Lecturer> id = memberService.findId(Lecturer.class, lecturerId);
        Lecturer lecturer = id.get();
        List<Book> allBook = lecturer.getBooks();
        model.addAttribute("allBook",allBook );
        return "items/bookList";
    }

    //createItem

    @GetMapping("/{lecturerId}/items/newBook")
    public String createBookForm(
            @PathVariable("lecturerId") String lecturerId,
            Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "items/createBookForm";

    }

    @PostMapping("/{lecturerId}/items/newBook")
    public String createBook(
            @PathVariable("lecturerId") String lecturerId,
            @Valid @ModelAttribute BookForm bookForm,
            BindingResult result,
            Model model){

        if (result.hasErrors()) {
            return "items/createBookForm";
        }



        if (itemService.findByName(Book.class, bookForm.getName()) != null) {
            result.reject("nameError", "중복된 이름입니다");
            return "items/createBookForm";
        }


        Optional<Lecturer> id = memberService.findId(Lecturer.class, lecturerId);
        Lecturer lecturer = id.orElse(null);

        String lectureName = bookForm.getLectureName();


        Lecture findLecture = itemService.findByName(Lecture.class, lectureName);

        if (findLecture== null) {
            result.reject("error","강의 이름을 확인해주세요");
            return "items/createBookForm";
        }


        Book book = new Book(bookForm.getPrice(), findLecture, bookForm.getName(), bookForm.getStockQuantity(), bookForm.getAuthor(), bookForm.getIsbn());
        assert lecturer != null;
        lecturer.addBook(book);
        itemService.save(book);
        return "redirect:/"+lecturerId+"/lecturerHome";
    }

    @GetMapping("/{lecturerId}/items/newLecture")
    public String createLectureForm(
            @PathVariable("lecturerId") String lecturerId,
            Model model) {
        model.addAttribute("lectureForm", new LectureForm());
        return "items/createLectureForm";
    }

    @PostMapping("/{lecturerId}/items/newLecture")
    public String createLecture(@Valid LectureForm lectureForm, BindingResult result
            , @PathVariable("lecturerId") String lecturerId) {
        if (result.hasErrors()) {
            return "items/createLectureForm";
        }
        Optional<Lecturer> id = memberService.findId(Lecturer.class, lecturerId);
        Lecturer lecturer = id.orElse(null);

        if (lectureForm.getLectureName() == null) {
            result.reject("noneLectureName","강의 이름을 작성해주세요");
            return "items/createLectureForm";
        }
        if (itemService.findByName(Lecture.class, lectureForm.getLectureName()) != null) {
            result.reject("duplicateError", "이미 있는 강의 이름입니다");
            return "items/createLectureForm";
        }



        Lecture lecture = new Lecture(lectureForm.getPrice(), lectureForm.getLectureName(), lecturer);

        assert lecturer != null;
        lecturer.getLectures().add(lecture);
        itemService.save(lecture);
        return "redirect:/"+lecturerId+"/lecturerHome";
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
        List<Lecture> allLecture = lecturer.getLectures();
        model.addAttribute("allLecture", allLecture);
        return "items/lectureList";
    }

    @GetMapping("/{studentId}/studentHome/lecturelist")
    public String myLecture(Model model,
                            @PathVariable("studentId") String Id) {
        Optional<Student> id = memberService.findId(Student.class, Id);
        Student student = id.get();
        List<Lecture> allLecture = student.getLectures();
        model.addAttribute("allLecture", allLecture);
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


    //parent
    @GetMapping("/{parentId}/parentHome/lecturelist")
    public String studentLecture(@PathVariable("parentId") String parentId,
                                 Model model) {
        Optional<Parent> id = memberService.findId(Parent.class, parentId);
        Parent parent = id.get();
        List<Lecture> allLecture = parent.getStudent().getLectures();
        model.addAttribute("allLecture", allLecture);
        return "items/lectureList";
    }
}