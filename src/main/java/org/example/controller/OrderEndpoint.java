package org.example.controller;

import org.example.classes.GetOrderInfoRequest;
import org.example.classes.GetOrderInfoResponse;
import org.example.classes.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@EnableWs
@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/ws/orders";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderEndpoint(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderInfoRequest")
    public GetOrderInfoResponse getOrderInfo(@RequestPayload GetOrderInfoRequest getOrderRequest) {
        GetOrderInfoResponse orderInfoResponse = new GetOrderInfoResponse();
        Order order = orderRepository.findOrderById(getOrderRequest.getOrderId());
        orderInfoResponse.setOrder(order);

        return orderInfoResponse;
    }
}
