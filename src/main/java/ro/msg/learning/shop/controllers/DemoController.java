package ro.msg.learning.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class DemoController {

    @RequestMapping
    String index() {
        return "index";
    }
}