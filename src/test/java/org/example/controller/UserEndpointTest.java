package org.example.controller;

import org.example.classes.GetUserInfoRequest;
import org.example.classes.GetUserInfoResponse;
import org.example.classes.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEndpointTest {

    @LocalServerPort
    private int port;

    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @BeforeEach
    public void init() {
        marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.example.classes");
    }

    @Test
    public void testUserEndpointResponse() {
        marshaller.setContextPath("org.example.classes");
        GetUserInfoRequest userInfoRequest = new GetUserInfoRequest();
        userInfoRequest.setUserId(3);

        User expected = new User();
        expected.setUserTaxNumber("120-9891-62");

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        GetUserInfoResponse userInfoResponse = (GetUserInfoResponse) webServiceTemplate
                .marshalSendAndReceive("http://localhost:" + port + "/ws/users", userInfoRequest);

        User actual = userInfoResponse.getUser();

        Assertions.assertEquals(expected.getUserTaxNumber(), actual.getUserTaxNumber());
    }

}
