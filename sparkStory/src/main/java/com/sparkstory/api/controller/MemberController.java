package com.sparkstory.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MemberController {

    @GetMapping("/members/login")
    public String login(){
        return "members/login";
    }
}
