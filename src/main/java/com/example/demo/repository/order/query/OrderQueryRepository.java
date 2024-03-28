package com.example.demo.repository.order.query;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtoBook() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(o->{
            List<OrderItemQueryDto> orderBooks = findOrderBooks(o.getOrderId());
            o.setOrderItems(orderBooks);
        });
        return orders;
    }

    public List<OrderQueryDto> findAllByDto_optimization_Book() {
        List<OrderQueryDto> orders = findOrders();
        Map<Long, List<OrderItemQueryDto>> orderBooksMap = findOrderBooksMap(toOrderIds(orders));
        orders.forEach(o -> o.setOrderItems(orderBooksMap.get(o.getOrderId())));
        return orders;
    }

    public List<OrderQueryDto> findOrderQueryDtoLecture() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(o->{
            List<OrderItemQueryDto> orderLectures = findOrderLectures(o.getOrderId());
            o.setOrderItems(orderLectures);
        });
        return orders;
    }
    public List<OrderQueryDto> findAllByDto_optimization_Lecture() {
        List<OrderQueryDto> orders = findOrders();
        Map<Long, List<OrderItemQueryDto>> orderLecuturesMap = findOrderLecuturesMap(toOrderIds(orders));
        orders.forEach(o -> o.setOrderItems(orderLecuturesMap.get(o.getOrderId())));
        return orders;
    }
    private Map<Long, List<OrderItemQueryDto>> findOrderBooksMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery(
                        "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i" +  // 앞에서 가져온 order에 대한것을 한번에 다 가져옴
                                " where oi.order.id in :orderIds", OrderItemQueryDto.class
                ).setParameter("orderIds", orderIds)
                .getResultList();
        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderLecuturesMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery(
                        "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice)" +
                                " from OrderItem oi" +
                                " join oi.item i" +  // 앞에서 가져온 order에 대한것을 한번에 다 가져옴
                                " where oi.order.id in :orderIds", OrderItemQueryDto.class
                ).setParameter("orderIds", orderIds)
                .getResultList();
        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }
    private static List<Long> toOrderIds(List<OrderQueryDto> result) {
        return result.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toList());
    }
    private List<OrderItemQueryDto> findOrderLectures(Long orderId) {
        return em.createQuery(
                        "select new com.example.demo.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice)" +
                                " from OrderItem oi" +
                                " join oi.item i" +
                                " where oi.order.id = : orderId",
                        OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
    private List<OrderItemQueryDto> findOrderBooks(Long orderId) {
        return em.createQuery(
                        "select new com.example.demo.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i" +
                                " where oi.order.id = : orderId",
                        OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
                        "select new com.example.demo.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderQueryDto.class
                )
                .getResultList();
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery(
                "select new" +
                        " com.example.demo.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d" +
                        " join o.orderItems oi" +
                        " join oi.item i", OrderFlatDto.class
        ).getResultList();
    }
}
