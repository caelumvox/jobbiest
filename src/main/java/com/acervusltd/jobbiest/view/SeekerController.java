package com.acervusltd.jobbiest.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acervusltd.jobbiest.db.SeekerTableGateway;
import com.acervusltd.jobbiest.model.Seeker;

@Controller
@RequestMapping("/profile")
public class SeekerController {

    @Autowired
    SeekerTableGateway seekerTableGateway;

    @RequestMapping(value = "/{seekerId}", method = RequestMethod.GET)
    public String getOpportunity(@PathVariable Integer seekerId, Model model) {
        Seeker seeker = seekerTableGateway.getSeeker(seekerId);

        model.addAttribute("seeker_id", seeker.getSeekerId());
        model.addAttribute("username", seeker.getUsername());
        model.addAttribute("email", seeker.getEmail());
        model.addAttribute("address", seeker.getAddress());
        model.addAttribute("city", seeker.getCity());
        model.addAttribute("state", seeker.getState());
        model.addAttribute("firstname", seeker.getFirstname());
        model.addAttribute("lastname", seeker.getLastname());
        model.addAttribute("created", seeker.getCreated());

        return "profile";
    }
}
