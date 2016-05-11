package com.acervusltd.jobbiest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }
    
    @RequestMapping(value="/error", method = RequestMethod.GET)
    public String getError() {
        return "error";
    }
}
