package com.acervusltd.jobbiest.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/opportunities")
public class OpportunitiesController {

    @RequestMapping(method = RequestMethod.GET)
    public String getOpportunity() {
        return "opportunities";
    }
}