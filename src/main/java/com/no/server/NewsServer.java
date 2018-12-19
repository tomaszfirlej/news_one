package com.no.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsServer {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

}
