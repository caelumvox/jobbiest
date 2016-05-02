package com.acervusltd.jobbiest.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acervusltd.jobbiest.model.Seeker;
import com.acervusltd.jobbiest.session.SeekerDetailsService;

@Controller
@RequestMapping("/profile")
public class SeekerController {

    @RequestMapping(method = RequestMethod.GET)
    public String getOpportunity(Model model) {
        Seeker seeker = SeekerDetailsService.getSeeker();

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
