package org.example.controller;

import org.example.classes.GetOrderInfoRequest;
import org.example.classes.GetOrderInfoResponse;
import org.example.classes.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderEndpointTest {

    @LocalServerPort
    private int port;

    Jaxb2Marshaller marshaller;

    @BeforeEach
    void init() {
        marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.example.classes");
    }

    @Test
    public void testOrderEndpointResponse() {
        GetOrderInfoRequest orderInfoRequest = new GetOrderInfoRequest();
        orderInfoRequest.setOrderId(2);

        Order expected = new Order();
        expected.setOrderDescription("OrderDescription2");

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        GetOrderInfoResponse response = (GetOrderInfoResponse) webServiceTemplate.marshalSendAndReceive(
                "http://localhost:" + port + "/ws/orders", orderInfoRequest);

        Order actual = response.getOrder();

        Assertions.assertEquals(expected.getOrderDescription(), actual.getOrderDescription());
    }


}
