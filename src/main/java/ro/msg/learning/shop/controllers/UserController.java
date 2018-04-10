package ro.msg.learning.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.services.UserService;
import ro.msg.learning.shop.strategy.NearestLocationStrategy;

import java.net.InetSocketAddress;
import java.net.Proxy;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getPproducts() {

        return userService.readUsers();
    }

}
