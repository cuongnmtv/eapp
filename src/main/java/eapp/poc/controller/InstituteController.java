package eapp.poc.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import eapp.poc.entity.Institute;
import eapp.poc.repo.InstituteRepo;
import eapp.poc.service.CommandService;

@Controller
public class InstituteController {

	@Autowired
	private CommandService cmdService;

	@Autowired
	private InstituteRepo instRepo;

	@GetMapping("/institute-form")
	public String form(Model m) {
		m.addAttribute("cmd", new Institute());
		return "institute-form";
	}

	@PostMapping("/save-institute")
	public String save(@ModelAttribute Institute inst) {
		inst.getContact().setName(inst.getName());
		inst.setDoe(new Date());
		cmdService.saveInstitute(inst);
		return "redirect:/institute-list";
	}

	@GetMapping("/institute-list")
	public String list(Model m) {
		//m.addAttribute("instList", instRepo.findAll());
		m.addAttribute("instList", instRepo.getInstituteList());
		return "institute-list";
	}
}
