package org.example.controller;

import org.example.classes.GetUserInfoRequest;
import org.example.classes.GetUserInfoResponse;
import org.example.classes.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@EnableWs
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/ws/users";

    private final UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserInfoRequest")
    public GetUserInfoResponse getUserInfo(@RequestPayload GetUserInfoRequest userInfoRequest) {
        GetUserInfoResponse getUserInfoResponse = new GetUserInfoResponse();
        User user = userRepository.findUserById(userInfoRequest.getUserId());
        getUserInfoResponse.setUser(user);

        return getUserInfoResponse;
    }

}
