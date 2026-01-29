package com.example.spring_intro.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(
            @RequestParam(value = "name", required = false) String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @ResponseBody
    @GetMapping("/hello-api")
    public String helloAPI(
            @RequestParam(value = "name", required = false)
            String name
    ) {
        return "This is API Response. Hello " + name;
    }

    @GetMapping("/hello-api-user")
    @ResponseBody
    public User helloAPIUser(
            @RequestParam("name") String name
    ) {
        User user = new User();
        user.setName("hello-user");
        return user;
    }

    @Getter
    @Setter
    static class User {
        private String name;
    }
}
