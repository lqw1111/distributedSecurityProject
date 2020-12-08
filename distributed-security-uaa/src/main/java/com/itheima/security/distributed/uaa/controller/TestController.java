package com.itheima.security.distributed.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
