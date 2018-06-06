package com.jank.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chenyafeng on 2018/6/5.
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @GetMapping("")
    public String index() {
        return "index";
    }

}
