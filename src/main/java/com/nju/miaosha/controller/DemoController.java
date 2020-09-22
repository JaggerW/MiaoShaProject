package com.nju.miaosha.controller;

import com.alibaba.fastjson.JSON;
import io.lettuce.core.output.StatusOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: jaggerw
 * @Description: DemoController
 * @Date: 2020/9/21
 */

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping("/test")
    public String thymeleaf(Model model){
        model.addAttribute("name","jaggerw");
        return "hello";
    }

}
