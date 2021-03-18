package org.example;

import org.example.classes.GetUserInfoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebServiceIntegrationTest {

    private Jaxb2Marshaller marshaller;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() throws Exception {
        marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetUserInfoRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetUserInfoRequest request = new GetUserInfoRequest();
        request.setUserId(1);

        Assertions.assertNotNull(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws/users", request));
    }

}
