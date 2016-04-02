package com.acervusltd.jobbiest.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acervusltd.jobbiest.db.OpportunityTableGateway;
import com.acervusltd.jobbiest.model.Opportunity;

@Controller
@RequestMapping("/opportunity")
public class OpportunityController {

    @Autowired
    OpportunityTableGateway opportunityTableGateway;
    
	@RequestMapping(value="/{opportunityId}", method = RequestMethod.GET)
	public String getOpportunity(@PathVariable Integer opportunityId, Model model) {
		Opportunity opportunity = opportunityTableGateway.getOpportunity(opportunityId);
		
		model.addAttribute("opp_id", opportunity.getOpportunityId());
		model.addAttribute("name", opportunity.getName());
		model.addAttribute("status", opportunity.getStatus());
		model.addAttribute("industry", opportunity.getIndustry());
		model.addAttribute("city", opportunity.getCity());
		model.addAttribute("state", opportunity.getState());
		
		return "opportunity";
	}
}
