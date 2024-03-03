package com.example.demo.service;

import com.example.demo.domain.delivery.Delivery;
import com.example.demo.domain.delivery.DeliveryStatus;
import com.example.demo.domain.discount.DiscountPolicy;
import com.example.demo.domain.discount.PolicyGrade;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.Student;
import com.example.demo.domain.order.OrderBase;
import com.example.demo.domain.order.OrderLecture;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//        if (findItem.getClass() == Lecture.class) {
//            Lecture findLecture = (Lecture) findItem;
//            findMember.addLecture(findLecture);
//            DiscountPolicy discountPolicy = new PolicyGrade();
//            discountPolicy.DiscountPrice(findLecture.getPrice(), findMember);
//            OrderLecture orderLecture = OrderLecture.createOrderLecture(findLecture, findLecture.getPrice());
//            OrderBase orderBase=OrderBase.
//        }

        Delivery delivery=new Delivery();
        delivery.setAddress(findMember.getAddress());
        delivery.setStatus(DeliveryStatus.준비);

        return 0L;
    }

}
