package com.example.demo.controller;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.Student;
import com.example.demo.domain.order.OrderBase;
import com.example.demo.domain.order.OrderBook;
import com.example.demo.domain.order.OrderStatus;
import com.example.demo.dto.LectureDTO;
import com.example.demo.dto.OrderBookDTO;
import com.example.demo.dto.OrderLectureDTO;
import com.example.demo.repository.OrderSearch;
import com.example.demo.service.MemberService;
import org.springframework.ui.Model;


import com.example.demo.repository.MemberRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/{studentId}/studentHome/orderBook")
    public String createBookForm(Model model,
                                 @PathVariable("studentId") String studentId) {

        List<Book> allBooks = itemService.findAll(Book.class);

        model.addAttribute("allBooks", allBooks);

        return "order/orderBookForm";
    }

    @PostMapping("/{studentId}/studentHome/orderBook")
    public String orderBook(@PathVariable("studentId") String studentId,
                            @RequestParam("bookId") String bookName,
                            @RequestParam("count") int count) {
        orderService.orderBase(studentId, bookName, count, Book.class);
        return "redirect:/" + studentId + "/orders";
    }

    @GetMapping("/{studentId}/studentHome/orderLecture")
    public String createLectureForm(Model model,
                                    @PathVariable("studentId") String studentId) {
        List<Lecture> allLectures = itemService.findAll(Lecture.class);
        model.addAttribute("allLectures", allLectures);
        return "order/orderLectureForm";
    }


    @PostMapping("/{studentId}/orderBook")
    public String orderListBook(Model model,
                                @PathVariable("studentId")String studentId,
                                @RequestParam("bookId")String bookId,
                                @RequestParam("count")int count) {

        orderService.orderBase(studentId, bookId, count, Book.class);
        Optional<Student> id = memberService.findId(Student.class, studentId);
        Student student = id.get();
        List<OrderBase> orders = student.getOrderBases();
        List<OrderBookDTO> orderBookDTOS = new ArrayList<>();
        for (OrderBase order : orders) {
            List<OrderBook> orderBooks = order.getOrderBooks();
            for (OrderBook orderBook : orderBooks) {
                Book book = orderBook.getBook();
                orderBookDTOS.add( new OrderBookDTO(book.getName(),
                        book.getLecture().getName(),
                        book.getName(),
                        book.getIsbn(),
                        order.getOrderDate(),
                        orderBook.getCount(),
                        book.getIsbn(),
                        order.getStatus(),
                        order.getDelivery().getStatus())
                );
            }

        }
        model.addAttribute("orderBookDTOS", orderBookDTOS);
        return "order/orderList2";
    }

    @PostMapping("/{studentId}/orderLecture")
    public String orderListLecture(Model model,
                            @PathVariable("studentId") String studentId,
                            @RequestParam("lectureId") String lectureId) {

        orderService.orderBase(studentId, lectureId, 1, Lecture.class);
        Optional<Student> id = memberService.findId(Student.class, studentId);
        Student student = id.get();
        List<OrderBase> orders = student.getOrderBases();
        List<OrderLectureDTO> lectureDTOs = new ArrayList<>();
        for (OrderBase order : orders) {
            OrderLectureDTO orderlectureDTO = new OrderLectureDTO(order.getId(), order.getOrderLecture().getLecture().getName(), order.getOrderDate(), order.getPrice());
            lectureDTOs.add(orderlectureDTO);
        }
        model.addAttribute("lectureDTOs", lectureDTOs);
        return "order/orderList";
    }

//    @GetMapping("/{studentId}/studentHome/orderStatus")
//    public String orderStatus(@PathVariable("studentId") String studentId,
//                              Model model) {
//        Optional<Student> id = memberService.findId(Student.class, studentId);
//        Student student = id.get();
//        List<OrderBase> orderBases = student.getOrderBases();
//        model.addAttribute("orderBases", orderBases);
//        return "order/orderStatus";
//    }

    @GetMapping("/{studentId}/studentHome/orderStatus")
    public String orderStatus(@PathVariable("studentId") String studentId,
                              @RequestParam(value = "orderStatus", required = false) OrderStatus orderStatus,
                              Model model) {
        Optional<Student> studentOptional = memberService.findId(Student.class, studentId);
        if (studentOptional.isEmpty()) {
            return "redirect:/login";
        }
        Student student = studentOptional.get();

        List<OrderBase> orderBases;
        if (orderStatus != null) {
            // 주문 상태에 따라 필터링된 주문 목록 가져오기
            orderBases = student.getOrderBases().stream()
                    .filter(order -> order.getStatus() == orderStatus)
                    .collect(Collectors.toList());
        } else {
            // 주문 상태를 지정하지 않은 경우 모든 주문 목록 가져오기
            orderBases = student.getOrderBases();
        }

        model.addAttribute("orderBases", orderBases);
        return "order/orderStatus";
    }
}
