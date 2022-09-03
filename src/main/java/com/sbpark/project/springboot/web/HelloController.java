package com.sbpark.project.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 json으로 반환하는 컨트롤러로 만들어주는 어노테이션
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
