package org.example.repository;

import org.example.classes.Order;

public interface OrderRepository {

    void addOrder(Order order);

    Order findOrderById(int id);

}
