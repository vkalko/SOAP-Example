package org.example.repository.impl;

import org.example.classes.Order;
import org.example.repository.OrderRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static final List<Order> orderList = new ArrayList<>();

    @PostConstruct
    private void insertOrders() {
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setOrderStatus("OrderStatus1");
        order1.setOrderDescription("OrderDescription1");

        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setOrderStatus("OrderStatus2");
        order2.setOrderDescription("OrderDescription2");

        Order order3 = new Order();
        order3.setOrderId(3);
        order3.setOrderStatus("OrderStatus3");
        order3.setOrderDescription("OrderDescription3");

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
    }

    @Override
    public void addOrder(@NonNull Order order) {
        orderList.add(order);
    }

    @Override
    public Order findOrderById(int id) {
        return orderList.stream().filter(order ->
                order.getOrderId() == id)
                .findFirst()
                .orElse(null);
    }
}
