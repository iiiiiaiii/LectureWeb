package com.example.demo.service;

import com.example.demo.domain.delivery.Delivery;
import com.example.demo.domain.delivery.DeliveryStatus;
import com.example.demo.pay.DiscountPolicy;
import com.example.demo.pay.PolicyGrade;
import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Student;
import com.example.demo.domain.order.OrderBase;
import com.example.demo.domain.order.OrderBook;
import com.example.demo.domain.order.OrderLecture;
import com.example.demo.pay.Save;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    private Long orderBase(Long memberId, Long itemId, int count, Class<?> entityClass) {
        Student findMember = memberRepository.findOne(Student.class, memberId);
        Object findItem = itemRepository.findOne(entityClass, itemId);
        if (findItem.getClass() == Lecture.class) {
            Lecture findLecture = (Lecture) findItem;
            findMember.addLecture(findLecture);
            int price = discountSet(findLecture.getPrice(), findMember);
            OrderLecture orderLecture = OrderLecture.createOrderLecture(findLecture, price);
            OrderBase orderBase = OrderBase.createLecture(findMember, orderLecture);
            orderRepository.save(orderBase);
            return orderBase.getId();
        }
        Book book = (Book) findItem;
        Delivery delivery=new Delivery();
        delivery.setStatus(DeliveryStatus.준비);
        delivery.setAddress(findMember.getAddress());

        int price = discountSet(book.getPrice(), findMember);
        OrderBook orderBook = OrderBook.createOrderBook(book, count, price);
        OrderBase orderBase = OrderBase.createBook(findMember, delivery, orderBook);
        orderRepository.save(orderBase);
        return orderBase.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        OrderBase order = orderRepository.findOne(orderId);

        //정보수정
        rollback(order);
        List<OrderBook> orderBooks = order.getOrderBooks();
        for (OrderBook orderBook : orderBooks) {
            order.getStudent().getLectures().remove(orderBook);
        }
        //주문 취소
        order.cancelBooks();
    }


    @Transactional
    public void cancelLecture(Long orderId) {
        OrderBase order = orderRepository.findOne(orderId);
        Student student = order.getStudent();
        rollback(order);
        student.getLectures().remove(order.getOrderLecture());
        order.cancelLecture();
    }

    private static void rollback(OrderBase order) {
        Student student = order.getStudent();
        int current = student.getPay();
        int newPrice = order.getPrice();
        student.setPay(current-newPrice);
        Save.gradeSet(student);
    }

    private static int discountSet(int findLecture, Student findMember) {
        DiscountPolicy discountPolicy = new PolicyGrade();
        return discountPolicy.DiscountPrice(findLecture, findMember);
    }


}
