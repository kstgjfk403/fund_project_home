package com.idgcapital.ufund.common.controller;

/**
 * Created by yi_zhang on 2017/12/18.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
