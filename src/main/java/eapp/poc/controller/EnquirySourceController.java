package eapp.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eapp.poc.entity.EnquirySource;
import eapp.poc.repo.EnquirySourceRepo;

@Controller
public class EnquirySourceController {

	@Autowired
	private EnquirySourceRepo enquirySourceRepo;

	@GetMapping("/sources")
	public String sources(Model m) {
		EnquirySource cmd = new EnquirySource();
		m.addAttribute("cmd", cmd);
		return "sources";
	}

	@PostMapping("/save-enquiry-source")
	public String save(@ModelAttribute EnquirySource cmd) {
		enquirySourceRepo.save(cmd);
		return "redirect:/sources";
	}

	@ModelAttribute("enquirySourceList")
	public List<EnquirySource> getEnquirySourceList() {
		return enquirySourceRepo.findAll();
	}
}
