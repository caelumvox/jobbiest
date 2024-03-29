package com.acervusltd.jobbiest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acervusltd.jobbiest.db.OpportunitiesTableGateway;
import com.acervusltd.jobbiest.model.Opportunity;
import com.acervusltd.jobbiest.model.Seeker;
import com.acervusltd.jobbiest.session.SeekerDetailsService;

@Controller
@RequestMapping("/opportunities")
public class OpportunitiesController {

    @Autowired
    OpportunitiesTableGateway opportunityTableGateway;

    @RequestMapping(method = RequestMethod.GET)
    public String getOpportunities(Model model) {
        Seeker seeker = SeekerDetailsService.getSeeker();
        model.addAttribute("seeker_id", seeker.getSeekerId());
        return "opportunities";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateOpportunity(Model model) {
        Seeker seeker = SeekerDetailsService.getSeeker();
        model.addAttribute("seeker_id", seeker.getSeekerId());
        return "opportunity_create";
    }
    
    @RequestMapping(value = "/{opportunityId}", method = RequestMethod.GET)
    public String showOpportunity(@PathVariable Integer opportunityId, Model model) {
        Opportunity opportunity = opportunityTableGateway.getOpportunity(opportunityId);

        model.addAttribute("opp_id", opportunity.getOpportunityId());
        model.addAttribute("seeker_id", opportunity.getSeekerId());
        model.addAttribute("name", opportunity.getName());
        model.addAttribute("status", opportunity.getStatus());
        model.addAttribute("industry", opportunity.getIndustry());
        model.addAttribute("address", opportunity.getAddress());
        model.addAttribute("city", opportunity.getCity());
        model.addAttribute("state", opportunity.getState());
        model.addAttribute("zip", opportunity.getZip());
        model.addAttribute("url", opportunity.getUrl());

        return "opportunity";
    }
}
