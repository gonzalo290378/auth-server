package com.bench.authserver.controllers;

import com.bench.users.commons.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
@Slf4j
public class OAuthUserController {

    //login: http://127.0.0.1:8090/ms-users/oauth2/authorization/frontend-app

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/search-username")
    public User findByUsername(@RequestParam String username) {
        HashMap<String, String> uriPathVariable = new HashMap<>();
        uriPathVariable.put("username", username);
        return restTemplate.getForObject("http://localhost:8090/ms-users/api/v1/users/{username}", User.class, uriPathVariable);
    }

    @GetMapping("/authorized")
    public Map<String, String> authorized(@RequestParam String code) {
        log.info("Calling authorized with {}", code);
        return Collections.singletonMap("code", code);
    }

}